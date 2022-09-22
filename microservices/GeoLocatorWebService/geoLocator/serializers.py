from rest_framework import serializers

from geoLocator.models import DeliveryZoneShop
from geoLocator.models import DeliveryZonesShop
from geoLocator.models import GeoLocatorCalc
from geoLocator.models import AddressClient
from django.contrib.gis.geos import Point

class GeoLocatorCalcZoneSerializer(serializers.ModelSerializer):
    class Meta:
        model = GeoLocatorCalc
        fields = '__all__'

    def create(self, geoLocator):
        addressClient = AddressClient.objects.create(latitude = geoLocator.address.latitude,longitude = geoLocator.address.longitude, street = geoLocator.address.street,city =
        geoLocator.address.city,state=geoLocator.address.state,cep = geoLocator.address.cep)
        addressClient.point_client = self.getPointClient(geoLocator.address.latitude,geoLocator.address.longitude)
        addressClient.save()
        dList = []
        for deliveryZones in geoLocator.deliveryZonesShop:
            for zone in deliveryZones.zones:
                dZone = DeliveryZoneShop.objects.create(latitude = zone.latitude, longitude = zone.longitude, radius = zone.radius,valuePrice = zone.valuePrice,
                zone=self.createAPolygonBasedInShopDeliveryZone(zone))
                dZone.get_centroid()
                deliveryZonesShop = DeliveryZonesShop.objects.create(idShopStore=deliveryZones.idShopStore,zones = dZone)
                dList.append(deliveryZonesShop)
        geoLocator = GeoLocatorCalc.objects.create(client_id_token = geoLocator.client_id_token, address = addressClient)
        for k in dList:
            geoLocator.deliveryZonesShop.add(k)
            geoLocator.save()
        
        return dList

    def createAPolygonBasedInShopDeliveryZone(self,deliveryZone):
        point = Point(x=deliveryZone.longitude,y=deliveryZone.latitude,srid=4326)
        point.transform(5880)
        return point.buffer(deliveryZone.radius*100)
    
    def getPointClient(self,lat,long):
            point = Point(x=long,y=lat,srid=4326)
            point.transform(5880)
            return point.buffer(20)

        