from django.db import models
from django.contrib.gis.db import models as gismodels
# Create your models here.


class DeliveryZoneShop(gismodels.Model):
    latitude = models.FloatField()
    longitude = models.FloatField()
    valuePrice = models.FloatField()
    radius = models.IntegerField()
    zone = gismodels.PolygonField(
        verbose_name='Area',
        spatial_index=True,
        null=True,
        blank=True,
        srid=5880,
    )

    def get_centroid(self):
        zone = getattr(self, 'zone')
        if zone:
            center = getattr(zone, 'centroid')
            return center
        return None

    _centroid = property(get_centroid)
    




class AddressClient(models.Model):
    latitude = models.FloatField()
    longitude = models.FloatField()
    street = models.CharField(max_length=255)
    city = models.CharField(max_length=255)
    state = models.CharField(max_length=20)
    cep = models.CharField(max_length=40)
    point_client = gismodels.PolygonField(
        verbose_name='Endereço Cliente',
        spatial_index=True,
        null=True,
        blank=True,
        srid=5880,
    )


class DeliveryZonesShop(models.Model):
    idShopStore = models.IntegerField()
    zones = models.ForeignKey(DeliveryZoneShop, on_delete=models.CASCADE)


class GeoLocatorCalc(models.Model):
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    client_id_token = models.CharField(max_length=255)
    address = models.OneToOneField(
        AddressClient,
        on_delete=models.CASCADE,
        primary_key=True,
    )

    deliveryZonesShop = models.ManyToManyField(
        DeliveryZonesShop,
        primary_key=False,
        blank=True,
        null=True
    )
