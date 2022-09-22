from django.contrib import admin
from django.contrib.gis import admin as adminGeo
from geoLocator.models import DeliveryZoneShop
from geoLocator.models import AddressClient
from geoLocator.models import GeoLocatorCalc
from geoLocator.models import DeliveryZonesShop
# Register your models here.

admin.site.register(DeliveryZoneShop,adminGeo.OSMGeoAdmin)
admin.site.register(AddressClient,adminGeo.OSMGeoAdmin)
admin.site.register(GeoLocatorCalc)
admin.site.register(DeliveryZonesShop)

