#include <iostream>
#include <vector>


using namespace std;

vector<int> BrojevnaAbeceda(char abeceda[], int kratnost){
  vector<int> abeceda_broj;
  int iter;

  for(iter = 0; iter < sizeof(abeceda); iter++){
	  abeceda_broj.push_back(abeceda[iter]);
  }

  return abeceda_broj;
}
 
vector<vector<int>> GenerirajKodneRijeci(vector<int> abeceda_broj, int kratnost) {
  int iter, kvocijent, ostatak;
  vector<int> kodna_rijec;
  vector<vector<int>> kodne_rijeci;
  
  
  for(iter = 0; iter < abeceda_broj.size(); iter++){
	kvocijent = abeceda_broj[iter];

	do{
	  ostatak = kvocijent%kratnost;
	  kodna_rijec.push_back(ostatak);
	  kvocijent /= kratnost;
	} while(0 != kvocijent);

	reverse(kodna_rijec.begin(), kodna_rijec.end());
	kodne_rijeci.push_back(kodna_rijec);
	kodna_rijec.clear();
  }
  
  return kodne_rijeci;

}