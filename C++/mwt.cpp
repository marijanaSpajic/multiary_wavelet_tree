#include <iostream>
#include <cstring>
#include <math.h>
#include <sstream>
#include <fstream>
#include <assert.h>
#include "mwt.h"

using namespace std;

int main(int argc, char* argv[]){

  if (argc != 3){
	cout << "Expecting " << 2 << "parameters: filename, arity." <<endl;
	exit(1);
  }

  int ary = atoi(argv[2]);
  
  FILE *file;
  file = fopen(argv[1], "r");
  
  fseek(file, 0, SEEK_END);
  int length = ftell(file);
  fseek(file, 0, SEEK_SET);

  char *input;
  input = new char[length+5];

  int character, input_size = 0;
  while((character = fgetc(file)) != EOF){
	  input[input_size] = character;
	  input_size++;
  }

  fclose(file);

  const char *newLetter;

  vector<int> numbered_alphabet,
	code_word,
	layer;
  
  vector<vector<int>> code_words, 
	layers, 
	coded_input;

  char letter,
	   alphabet[4] = {};
  
  int iter1, 
	  iter2 = 0,
	  layer_number;



  // Create alphabet
  for (iter1 = 0; iter1 < input_size; iter1++){
	 letter = input[iter1];
	 newLetter = strchr(alphabet, letter);
	 
	 if(NULL == newLetter){
	   alphabet[iter2] = letter;
	   iter2++;
	 }
	 
  }

  layer_number = ceil((7/(log((double)ary)/log(double(2)))));
  numbered_alphabet = NumberedAlphabet(alphabet);
  code_words = GenerateCodeAlphabet(numbered_alphabet, ary, layer_number);


  // Coded input
  for (iter1 = 0; iter1 < input_size; iter1++){
    for (iter2 = 0; iter2 < sizeof(alphabet); iter2++){
	  if (input[iter1] == alphabet[iter2])
		  coded_input.push_back(code_words[iter2]);
	}
  }

  // Create layers
  for (iter1 = 0; iter1 < layer_number; iter1++){
    for (iter2 = 0; iter2 < coded_input.size(); iter2++)
	  layer.push_back(coded_input[iter2][iter1]);
    layers.push_back(layer);
	layer.clear();
  }
  reverse(layers.begin(), layers.end());

  // Generate MWT
 
  // Start with root 
  node *root, test;
  root = InitRoot(ary);
  root->data = layers[layer_number-1];

  // Iterate over root (top layer) elements and recursively
  // generate MW tree
  for (iter1 = 0; iter1 < root->data.size(); iter1++){
    CreateBranch(root, layers, layer_number-1, ary, iter1);
  }

  for(iter1 = 0; iter1 < code_words.size(); iter1++)
	code_word = code_words[iter1];


  code_word = WordCoder((int)'A', ary, layer_number);

  int rank = Rank(5, code_word, root);
  cout << rank << endl;

  /* Various tests

  for (iter1 = layer_number - 1 ; iter1 >=0; iter1--){
	for (iter2 = 0; iter2 < layers[iter1].size(); iter2++)
		cout << layers[iter1][iter2];
	cout << endl;
  }

  for (iter2 = 0; iter2 < root.data.size(); iter2++)
    cout << root.data[iter2];

  for (iter1 = 0; iter1 < layer_number; iter1++){
	for (iter2 = 0; iter2 < layers[iter1].size(); iter2++)
		cout << layers[iter1][iter2];
	cout << endl;
  }
  
  for (iter1 = 0; iter1 < coded_input.size(); iter1++){
	  for (iter2 = 0; iter2 < coded_input[iter1].size(); iter2++)
		  cout << coded_input[iter1].at(iter2);
	  cout << endl;

  for (iter2 = 0; iter2 < root.data.size(); iter2++)
		cout << root.data[iter2];
	cout << endl;
  for (iter1 = 0; iter1 < coded_input.size(); iter1++){
	  for (iter2 = 0; iter2 < coded_input[iter1].size(); iter2++)
		  cout << coded_input[iter1].at(iter2);
	  cout << endl;
	}

	cout << layer_number;

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