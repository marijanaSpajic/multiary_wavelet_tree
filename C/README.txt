Program se pokreće na platformi Bio-Linux 8.

Za pokretanje je potrebno preuzeti sadržaj direktorija 'C' na računalo ili klonirati git repozitorij. Ako git nije instaliran, isti je moguće instalirati naredbom:

> sudo apt-get install git

Potrebno se pozicionirati na mjesto gdje se želi klonirati repozitorij te izvršiti sljedeću naredbu:

> git clone https://github.com/marijanaSpajic/multiary_wavelet_tree.git 

Nakon toga se potrebno pozicionirati u lokalni repozitorij u direktorij 'C'. Program se pokreće na sljedeći način:

> gcc mwt.c -o mwt -lm ("-lm" je potrebno upisati zbog korištenja math.h u kodu)
> ./mwt 'ulazna_datoteka' 'kratnost_stabla'

'ulazna_datoteka' je naziv datoteke koja sadrži ulazni niz znakova. Ako se program želi pokrenuti za datoteku koja se ne nalazi u direktoriju, potrebno je napisati put do te datoteke. 
'kratnost_stabla' je cijeli broj koji označava kratnost stabla

Primjer pokretanja programa za primjer koji se nalazi u direktoriju:

> gcc mwt.c -o mwt -lm
> ./mwt input_10 4

Ispisuje je sljedeće:

> Creating tree finished.
> Continue (c) or exit (e)?

Ako je upisano 'c' program ispisuje sljedeće:

> Type your command.
> Characters in the tree: A C T G
> Possible commands:
> 1) RANK - r 'position' 'character'	MAX_POSITION = 1000020
> 2) SELECT - s 'num_of_character' 'character' 

Za računanje ranka je potrebno upisati npr.:

> r 57 A

Za računanje selecta je potrebno upisati npr.:

> s 15 C

Ovisno o zadanoj naredbi, program ispisuje sljedeće:

> Calculated rank: 14
ili
> 15. character C is on position: 80

Nakon toga se ponovno nudi opcija nastavka ili kraja. 

Napomena: 
Ostali testni primjeri se nalaze u direktoriju 'generate_inputs'.