# Generated by Django 4.1.1 on 2022-09-21 00:51

import django.contrib.gis.db.models.fields
from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('geoLocator', '0005_geolocatorcalc_created_at_geolocatorcalc_updated_at'),
    ]

    operations = [
        migrations.AddField(
            model_name='deliveryzoneshop',
            name='zone',
            field=django.contrib.gis.db.models.fields.PolygonField(blank=True, null=True, srid=5880, verbose_name='Area'),
        ),
    ]