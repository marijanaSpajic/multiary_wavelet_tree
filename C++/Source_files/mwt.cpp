#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

int main(void){

  const char ulaz[] = "ACCTAGG", *novoSlovo;
  char slovo, abeceda[256] = {};
  int iter1, iter2 = 0, kratnost = 4;

  for (iter1 = 0; iter1 < sizeof(ulaz); iter1++){
	 slovo = ulaz[iter1];
	 novoSlovo = strchr(abeceda, slovo);
	 
	 if(NULL == novoSlovo){
	   abeceda[iter2] = slovo;
	   iter2++;
	 }
	 
  }
	
  for (iter1 = 0; iter1 < sizeof(abeceda); iter1++)
	  if (abeceda[iter1] != NULL)
		cout << (int)abeceda[iter1] << endl;

  getchar();
  return 0;
}