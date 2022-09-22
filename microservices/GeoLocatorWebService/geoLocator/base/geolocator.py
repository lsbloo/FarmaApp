
import json
from django.contrib.gis.geos import Point
from geoLocator.base.farmaserializer import DeliveryZonesShopDTO
from geoLocator.base.farmaserializer import DeliveryZoneShopDTO
import json

from django.forms.models import model_to_dict
class GeoLocate(object):

    def __init__(self,addressClient, shopData):
        self.addressClient = addressClient
        self.shopData = shopData
        self.srid_texas=4326
        self.srid_brazil=5880
        self.best_offerts = []
        self.best_offerts_shop = []
    
    def calculateShopAroundAddressClient(self):

        lat_address_client = self.addressClient.latitude
        lon_address_client = self.addressClient.longitude
    
        point = self.getPointClient(lat=lat_address_client,long=lon_address_client)

        for shop in self.shopData:
            dataZone = {shop.idShopStore : shop.zones}
            self.calculateBestOffer(point,dataZone)
        
        valuePriceBest = []

        for offer in self.best_offerts:
            valuePriceBest.append(offer.valuePrice)
        
        if len(valuePriceBest) != 0 and valuePriceBest != None:
            best_value_price = min(valuePriceBest)
            best_offer_delivery_zone = self.best_offerts[valuePriceBest.index(best_value_price)]
            best_offer_shop_id = self.best_offerts_shop[valuePriceBest.index(best_value_price)]

            #print(best_offer_shop_id)
            #print(best_offer_delivery_zone.valuePrice)
            list_zones = []
            list_zones.append(best_offer_delivery_zone)
            object_result = DeliveryZonesShopDTO(best_offer_shop_id,None)
            dict_result = {best_offer_shop_id: best_offer_delivery_zone}

            best_offer_dto = DeliveryZoneShopDTO(best_offer_delivery_zone.latitude,best_offer_delivery_zone.longitude,best_offer_delivery_zone.valuePrice,best_offer_delivery_zone.radius)

            return json.dumps({"shopStore": object_result.__dict__, "deliveryZone": best_offer_dto.__dict__})
        else:
            return json.dumps({"shopStore": None, "deliveryZone": None})

    def calculateBestOffer(self,pointClient, deliveryZone):
        key = self.get_key(deliveryZone)
        value = deliveryZone.get(key)
        if value.zone.contains(pointClient):
            self.best_offerts.append(value)
            self.best_offerts_shop.append(key)
        

    def get_key(self,dict_ir):
        for key,value in dict_ir.items():
            return key

    def getPointClient(self,lat,long):
            point = Point(x=long,y=lat,srid=self.srid_texas)
            point.transform(self.srid_brazil)
            return point

    def createAPolygonBasedInShopDeliveryZone(self,deliveryZone):
        point = Point(x=deliveryZone.longitude,y=deliveryZone.latitude,srid=self.srid_texas)
        point.transform(self.srid_brazil)
        return point.buffer(deliveryZone.radius)

