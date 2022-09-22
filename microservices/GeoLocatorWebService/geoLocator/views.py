from django.shortcuts import render
from rest_framework import viewsets
from rest_framework import permissions
from geoLocator.serializers import GeoLocatorCalcZoneSerializer
from geoLocator.models import DeliveryZoneShop
from django.views.decorators.csrf import csrf_exempt
from django.http import HttpResponse
from geoLocator.base.farmaserializer import FarmaSerializer
from geoLocator.base.geolocator import GeoLocate
# Create your views here.

class GeoLocatorCalcZoneViewSet(viewsets.ModelViewSet):
    serializer_class = GeoLocatorCalcZoneSerializer
    queryset =  DeliveryZoneShop.objects.all()


@csrf_exempt
def calcZone(request):
    response_test = ""
    serializerJson = FarmaSerializer()
    if request.method == "POST":
        body_unicode = request.body.decode('utf-8')
        geoLocator = serializerJson.convertDataToGeoLocatorDeliveryZoneDTO(body_unicode)
        serializer = GeoLocatorCalcZoneSerializer()
        dList = serializer.create(geoLocator)
        geoLocate = GeoLocate(geoLocator.address,dList)
        response_test = geoLocate.calculateShopAroundAddressClient()
        

    print("######################## RESPONSE ####################################")
    print()
    print(response_test)
    print()
    print("######################################################################")
    return HttpResponse(response_test,content_type="application/json")
   