!/bin/sh

cd src/csv
rm *.csv
wget http://standards-oui.ieee.org/oui36/oui36.csv
wget http://standards-oui.ieee.org/oui28/mam.csv
wget http://standards-oui.ieee.org/oui/oui.csv
cd ../..

