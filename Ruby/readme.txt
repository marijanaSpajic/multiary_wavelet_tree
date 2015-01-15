Program je moguće pokrenuti u Linux i Windows okruženju.

Za pokretanje programa potrebno je:
1. Instalirati Ruby. Pri izradi je korištena verzija Ruby 2.2.0.
2. Ako je potrebno, omogućiti pokretanje Rubyja iz komandne linije.
3. Staviti mwt.rb i ulazne datoteke u isti direktorij.

Program se poziva upisom sljedeće naredbe u komandnu liniju:

	ruby mwt.rb <ime_datoteke> <kratnost> <rank/select> <pozicija/rank> <znak>

   gdje je:

   <kratnost>: cijeli broj
   <rank/select>: odabir funkcije, "rank" ili "select" (bez navodnika)
   <pozicija/rank>: cjelobrojni argument; za fju rank traži se pozicija, za select se traži rang
   <znak>: traženi znak ("A", "C", "G", "T", bez navodnika)

  Testni primjer:

	ruby mwt.rb test 4 rank 15 A
	
