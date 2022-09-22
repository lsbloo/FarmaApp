import json
from geoLocator.models import DeliveryZoneShop
from geoLocator.models import AddressClient
from geoLocator.models import GeoLocatorCalc
from geoLocator.models import DeliveryZonesShop
from django.forms.models import model_to_dict

class FarmaSerializer(object):

    def convertDataToDeliveryZoneShop(self,data):
        dataJson = json.loads(data)
        deliveryZone = DeliveryZoneShop(dataJson['latitude'],dataJson['longitude'],dataJson['valuePrice'],dataJson['radius'])
        return deliveryZone
    
    def convertDeliveryZoneShopToJson(self,data):
        jsonStr = json.dumps(model_to_dict(data))
        return jsonStr


    def convertDataToGeoLocatorDeliveryZoneDTO(self,data):
        print("######################## REQUEST ####################################")
        dataJson = json.loads(data)
        print(dataJson)
        print("#####################################################################")
        addressDTOData = dataJson["homeItemDTO"]["addressDTO"]
        addressClient = AddressDTO(latitude = addressDTOData['latitude'], 
        longitude = addressDTOData['longitude'],street = addressDTOData['street'],city = addressDTOData['city'],state = addressDTOData['state'],
        cep = addressDTOData['cep'])
        shopStoreData = dataJson["shopStore"]
        zones = []
        deliveryZonesShop = []
        for storeData in shopStoreData:
            for deliveryZone in storeData['deliveryZones']:
                zone = DeliveryZoneShopDTO(deliveryZone['latitude'],deliveryZone['longitude'],deliveryZone['valuePrice'],deliveryZone['radius'])
                zones.append(zone)
            
            deliveryZoneShop = DeliveryZonesShopDTO(storeData['idShopStore'],zones)
            deliveryZonesShop.append(deliveryZoneShop)
            zones = []

        clientIdToken = dataJson["homeItemDTO"]["client_id_token"]
        geoLocatorCalc = GeoLocatorCalcDTO(clientIdToken,addressClient,deliveryZonesShop)

        return geoLocatorCalc



class AddressDTO(object):
    def __init__(self,latitude,longitude,street,city,state,cep):
        self.latitude = latitude
        self.longitude = longitude
        self.street = street
        self.city = city
        self.state = state
        self.cep = cep

class DeliveryZonesShopDTO(object):
    def __init__(self,idShopStore,zones):
        self.idShopStore = idShopStore
        self.zones = zones

class DeliveryZoneShopDTO(object):
    def __init__(self,latitude,longitude,valuePrice,radius):
        self.latitude=latitude
        self.longitude=longitude
        self.valuePrice=valuePrice
        self.radius=radius


class GeoLocatorCalcDTO(object):
    def __init__(self, client_id_token,address,deliveryZones):
        self.client_id_token = client_id_token
        self.address = address
        self.deliveryZonesShop = deliveryZones
