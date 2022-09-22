# Generated by Django 4.1.1 on 2022-09-20 01:30

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='AddressClient',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('latitude', models.FloatField()),
                ('longitude', models.FloatField()),
                ('street', models.CharField(max_length=255)),
                ('city', models.CharField(max_length=255)),
                ('state', models.CharField(max_length=20)),
                ('cep', models.CharField(max_length=40)),
            ],
        ),
        migrations.CreateModel(
            name='DeliveryZoneShop',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('latitude', models.FloatField()),
                ('longitude', models.FloatField()),
                ('valuePrice', models.FloatField()),
                ('radius', models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name='DeliveryZonesShop',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('idShopStore', models.IntegerField()),
                ('zones', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='geoLocator.deliveryzoneshop')),
            ],
        ),
        migrations.CreateModel(
            name='GeoLocatorCalc',
            fields=[
                ('client_id_token', models.CharField(max_length=255)),
                ('address', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, primary_key=True, serialize=False, to='geoLocator.addressclient')),
                ('deliveryZonesShop', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='geoLocator.deliveryzonesshop')),
            ],
        ),
    ]
