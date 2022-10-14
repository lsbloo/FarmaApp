import os


DEF_PRODUCT_URL_FKTORY = "https://farmadelivery.herokuapp.com/products/fktory"

URL_ENDPOINT_SENDER = os.environ.get('URL_ENDPOINT_POST', 'DONT SETTER')

if URL_ENDPOINT_SENDER == "DONT SETTER":
    URL_ENDPOINT_SENDER = DEF_PRODUCT_URL_FKTORY
