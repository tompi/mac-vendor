#!/bin/sh

for f in "mam" "oui36" "oui"; do
	echo "[" > $f.json
	tail -n +2 src/csv/$f.csv | \
		sed 's/#//g' | \
		csvtool -t ',' format "%(2)#%(3)\n" "src/csv/$f.csv" | 
		sed 's/"/\\"/g' > "$f.tmp"
	csvtool -t '#' format "{ \"mac\": \"%(1)\", \"vendor\": \"%(2)\" },\n" "$f.tmp" >> $f.json
	rm $f.tmp
	echo "]" >> $f.json
done

