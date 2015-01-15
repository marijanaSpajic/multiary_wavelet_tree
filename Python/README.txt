Program je napisan za Python2.7.

Program se pokreće na način da se u komandnu liniju napiše:
python main.py kratnost_stabla ulazna_datoteka

kratnost_stabla - treba biti cijeli broj, vrijednost [2-127]
ulazna_datoteka - put do datoteke, ako se nalazi u istom direktoriju onda je dovoljan samo naziv

Primjer poziva programa:
python main.py 15 input_10

Ako se napravi git pull i pozicionira u ovaj direktorij, poziv programa je moguć na sljedeći način:
python main.py 15 ../generated_inputs/inputs/input_X
gdje X može biti broj između 1 i 10 jer u navedenom direktoriju postoji 10 datoteka različitih duljina, a input_10 je najveća od svih.
(Spomenute datoteke su genomi bakterije Escherichie coli, a datoteke su generirane s python skriptom)

Nakon što je program pokrenut, u na zaslon će se ispisati sljedeće:

Type your command.
Possible symbols:
A C G T

Possible commands:
1) rank   -> r(position,symbol)     max position = 1000020
2) select -> s(rank,symbol)
3) exit   -> e

Primjer unosa naredbe:
r(999999,A)

Na zaslonu će se, osim rezultata, ispisati i vrijeme potrebno za izračun funkcije izraženo u sekundama.
Nakon izvršavanja naredbe (rank ili select), program će nuditi mogućnost unosa novih naredbi
osim ako korisnik ne upiše "e" i time izađe iz programa.
