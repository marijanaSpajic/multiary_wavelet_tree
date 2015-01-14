#include <iostream>
#include <cstring>
#include <math.h>
#include "mwt.h"

using namespace std;

int main(void){

  const char ulaz[] = "ACCTAGG", *novoSlovo;
  vector<int> abeceda_broj, kodna_rijec, sloj;
  vector<vector<int>> kodne_rijeci, slojevi, kodirani_ulaz;
  char slovo, abeceda[4] = {};
  int iter1, 
	  iter2 = 0, 
	  kratnost = 4,
	  broj_slojeva;


  // Stvaranje abecede iz ulaznog niza
  for (iter1 = 0; iter1 < sizeof(ulaz); iter1++){
	 slovo = ulaz[iter1];
	 novoSlovo = strchr(abeceda, slovo);
	 
	 if(NULL == novoSlovo){
	   abeceda[iter2] = slovo;
	   iter2++;
	 }
	 
  }

  abeceda_broj = BrojevnaAbeceda(abeceda, kratnost);
  kodne_rijeci = GenerirajKodneRijeci(abeceda_broj, kratnost);
  broj_slojeva = ceil((7/(log((double)kratnost)/log(double(2)))));

  // Pretvaranje cijelog ulaznog niza u kodove
  for (iter1 = 0; iter1 < sizeof(ulaz); iter1++){
    for (iter2 = 0; iter2 < sizeof(abeceda); iter2++){
	  if (ulaz[iter1] == abeceda[iter2])
		  kodirani_ulaz.push_back(kodne_rijeci[iter2]);
	}
  }

  for (iter1 = 0; iter1 < broj_slojeva; iter1++){
    for (iter2 = 0; iter2 < kodirani_ulaz.size(); iter2++)
	  sloj.push_back(kodirani_ulaz[iter2][iter1]);
    slojevi.push_back(sloj);
	sloj.clear();
  }


 /* Razna testiranja

  for (iter1 = 0; iter1 < kodirani_ulaz.size(); iter1++){
	  for (iter2 = 0; iter2 < kodirani_ulaz[iter1].size(); iter2++)
		  cout << kodirani_ulaz[iter1].at(iter2);
	  cout << endl;
  }

  for (iter1 = 0; iter1 < broj_slojeva; iter1++){
	for (iter2 = 0; iter2 < slojevi[iter1].size(); iter2++)
		cout << slojevi[iter1][iter2];
	cout << endl;
  }

 for (iter1 = 0; iter1 < sizeof(abeceda); iter1++)
	  if (abeceda[iter1] != NULL)
		cout << abeceda[iter1] << endl;

  cout << endl;
  for (iter1 = 0; iter1 < kratnost; iter1++)
	  cout << abeceda_broj[iter1] << endl;

  for(iter1 = 0; iter1 < kodne_rijeci.size(); iter1++){
	kodna_rijec = kodne_rijeci[iter1];

	for(iter2 = 0; iter2 < kodna_rijec.size(); iter2++)
	  cout << kodna_rijec[iter2];
	cout << endl;
  }
  */

  getchar();
  return 0;
}