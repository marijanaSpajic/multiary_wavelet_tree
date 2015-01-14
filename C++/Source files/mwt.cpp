#include <iostream>
#include <cstring>
#include <math.h>
#include "mwt.h"

using namespace std;

int main(void){

  const char input[] = "ACCTAGG", *newLetter;
  vector<int> numbered_alphabet, code_word, layer;
  vector<vector<int>> code_words, layers, coded_input;
  char letter, alphabet[4] = {};
  int iter1, 
	  iter2 = 0, 
	  ary = 4,
	  layer_number;


  // Stvaranje abecede iz ulaznog niza
  for (iter1 = 0; iter1 < sizeof(input); iter1++){
	 letter = input[iter1];
	 newLetter = strchr(alphabet, letter);
	 
	 if(NULL == newLetter){
	   alphabet[iter2] = letter;
	   iter2++;
	 }
	 
  }

  numbered_alphabet = NumberedAlphabet(alphabet, ary);
  code_words = GenerateCodeWords(numbered_alphabet, ary);
  layer_number = ceil((7/(log((double)ary)/log(double(2)))));

  // Pretvaranje cijelog ulaznog niza u kodove
  for (iter1 = 0; iter1 < sizeof(input); iter1++){
    for (iter2 = 0; iter2 < sizeof(alphabet); iter2++){
	  if (input[iter1] == alphabet[iter2])
		  coded_input.push_back(code_words[iter2]);
	}
  }

  for (iter1 = 0; iter1 < layer_number; iter1++){
    for (iter2 = 0; iter2 < coded_input.size(); iter2++)
	  layer.push_back(coded_input[iter2][iter1]);
    layers.push_back(layer);
	layer.clear();
  }

   /* Razna testiranja


  for (iter1 = 0; iter1 < coded_input.size(); iter1++){
	  for (iter2 = 0; iter2 < coded_input[iter1].size(); iter2++)
		  cout << coded_input[iter1].at(iter2);
	  cout << endl;
  }

  for (iter1 = 0; iter1 < layer_number; iter1++){
	for (iter2 = 0; iter2 < layers[iter1].size(); iter2++)
		cout << layers[iter1][iter2];
	cout << endl;
  }

 

 for (iter1 = 0; iter1 < sizeof(alphabet); iter1++)
	  if (alphabet[iter1] != NULL)
		cout << alphabet[iter1] << endl;

  cout << endl;
  for (iter1 = 0; iter1 < ary; iter1++)
	  cout << numbered_alphabet[iter1] << endl;

  for(iter1 = 0; iter1 < code_words.size(); iter1++){
	code_word = code_words[iter1];

	for(iter2 = 0; iter2 < code_word.size(); iter2++)
	  cout << code_word[iter2];
	cout << endl;
  }
  */

  getchar();
  return 0;
}