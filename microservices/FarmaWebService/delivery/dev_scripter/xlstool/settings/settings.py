#

import os

PATH_ARCHIVE_ANVISA_MED = os.environ.get('PATH_ARCHIVE_ANVISA', 'DONT SETTER')
PATH_ARCHIVE_ANVISA_ANT = os.environ.get('PATH_ARCHIVE_ANVISA_ANT', 'DONT SETTER')

if PATH_ARCHIVE_ANVISA_MED == "DONT SETTER":
    PATH_ARCHIVE_ANVISA_MED = "C:\\Users\\osval\\Documents\\University\\PUC-POS-EngSoftware\\POS\\anvisa.xls"
if PATH_ARCHIVE_ANVISA_ANT == "DONT SETTER":
    PATH_ARCHIVE_ANVISA_ANT = "C:\\Users\\osval\\Documents\\University\\PUC-POS-EngSoftware\\POS\\anvisa_ant.xls"