#include <iostream>
#include <cstring>
#include <math.h>
#include "mwt.h"

using namespace std;

int main(void){

  const char ulaz[] = "ACCTAGG", *novoSlovo;
  vector<int> abeceda_broj, kodna_rijec;
  vector<vector<int>> kodne_rijeci;
  char slovo, abeceda[256] = {};
  int iter1, 
	  iter2 = 0, 
	  kratnost = 4,
	  broj_slojeva;

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
  cout << broj_slojeva << "\n";




/* Razna testiranja

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