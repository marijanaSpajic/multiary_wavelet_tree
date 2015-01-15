Program je pisan tako da se ekvivalentno pokreće u Linux i Windows okruženju.

Ono što je potrebno:
1.Instalirati Perl. Verzija koja je korištena za izradu je Perl 5.
	Mnogi operacijski sustavi (npr. većina Linuxa, kao i Bio-linux) dolaze sa predinstaliranim Perlom.
2.Ukoliko je potrebno, treba podesiti da se Perl pokreće iz komandne linije
3.Postaviti mwt.pl i željene inpute u isti direktorij
4.Pokrenuti tako da se prvo upiše perl, zatim mwt.pl i zatim ime datoteke koju treba obraditi
5.Osim toga, treba nakon toga upisati i sljedeće parametre:
	-kratnost, cijeli broj
	-"rank" ili "select", ovisno o željenoj funkciji
	-cijeli broj, za rank je to pozicija, a za select je to rank
	-"A", "C", "G" ili "T", znakovi nad kojima se vrši funkcija

Za pokretanje testnog primjera (nalazi se već u direktoriju \perl):
perl mwt.pl test 5 rank 10 A
Rezultat izvođenja je: 2	
	
Primjer pokretanja iz komandne linije:

perl mwt.pl input_2 5 rank 10 C
Rezultat ovog primjera bi trebao biti 3.

perl mwt.pl input_4 6 select 5 A
Rezultat ovog izvođenja bi trebao biti 24.

Napomena:
input_2 i input_4 nalaze se u multiary_wavelet_tree\generate_inputs\inputs i potrebno ih je na opisani način koristiti