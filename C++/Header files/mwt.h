#include <iostream>
#include <vector>


using namespace std;

// Funkcija koja pretvara slova abecede u njihovu ACII vrijednost
vector<int> NumberedAlphabet(char alphabet[], int ary){
  vector<int> numbered_alphabet;
  int iter;

  for(iter = 0; iter < sizeof(alphabet); iter++){
	  numbered_alphabet.push_back(alphabet[iter]);
  }

  return numbered_alphabet;
}
 
// Funkcija koja brojevnu abecedu pretvara u popis kodnih rijeci s obzirom na ary
vector<vector<int>> GenerateCodeWords(vector<int> numbered_alphabet, int ary) {
  int iter, quotient, remainder;
  vector<int> code_word;
  vector<vector<int>> coded_words;
  
  
  for(iter = 0; iter < numbered_alphabet.size(); iter++){
	quotient = numbered_alphabet[iter];

	do{
	  remainder = quotient%ary;
	  code_word.push_back(remainder);
	  quotient /= ary;
	} while(0 != quotient);

	reverse(code_word.begin(), code_word.end());
	coded_words.push_back(code_word);
	code_word.clear();
  }
  
  return coded_words;

}