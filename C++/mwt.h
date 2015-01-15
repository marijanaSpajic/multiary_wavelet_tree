#include <iostream>
#include <cstdlib>
#include <stdlib.h>
#include <cstring>
#include <vector>
#include <assert.h>
#include <algorithm>

using namespace std;

// Translate alphabet letters into ASCII values
// Input: alphabet in char
// Output: vector of numbered alphabet symbols (ASCII)
vector<int> NumberedAlphabet(char alphabet[]){
  vector<int> numbered_alphabet;
  int iter;

  for(iter = 0; iter < sizeof(alphabet); iter++){
	numbered_alphabet.push_back(alphabet[iter]);
  }

  return numbered_alphabet;
}

// Translates a numbered alphabet character into code word (depending on arity)
vector<int> WordCoder(int quotient, int ary, int number_of_digits){
  int digits = 1, remainder = 0;
  vector<int> code_word;
  
  // Calculate digit in new base and add to code word vector
  while(0 != quotient){
	  remainder = quotient%ary;
	  code_word.push_back(remainder);
	  digits++;
	  quotient /= ary;
	}
	while(digits < (number_of_digits+1)){
	  code_word.push_back(0);
	  digits++;
	}

	std::reverse(code_word.begin(), code_word.end());
	return code_word;
}
 
// Translates numbered alphabet into coded words (depending on arity)
// Input: numbered alphabet, arity, number of layers = number of digits
// Output: Vector of coded alphabet symbols
vector<vector<int> > GenerateCodeAlphabet(vector<int> numbered_alphabet, int ary, int number_of_digits) {
  int iter, quotient, remainder = 0;
  vector<int> code_word;
  vector<vector<int> > coded_words;
  
  // Iterate over numbered alphabet (ASCII)
  for(iter = 0; iter < numbered_alphabet.size(); iter++){

	code_word = WordCoder(numbered_alphabet[iter], ary, number_of_digits);
	coded_words.push_back(code_word);	
	code_word.clear();
  }
  
  return coded_words;

}

// Structure of a MW tree node
typedef struct node {
  vector<int> data;
  vector<node*> next;  
} node;

// Initializes non-root node
// Input: arity
// Output: pointer to new node
node* InitNode(int ary){
  node *current = new node;
  for(int iter = 0; iter < ary; iter++){
	current->next.push_back(NULL);
  }
  return current;
}

// Initalizes root node
// Input: arity
// Output: pointer to new root
node* InitRoot(int ary){
  node *root = new node;
  for(int iter = 0; iter < ary; iter++){
    root->next.push_back(NULL);
  }
  return root;
}

// Recursive; generates a single MW tree branch (down to the root)
// Input: root node, layered symbol sequences, top layer, arity, index in sequence
void CreateBranch(node *parent, vector<vector<int> > layers, int layer, int ary, int index){
  node *current;

  // Return if at bottom layer
  if (layer == 0)
	return;

  // Create child if it does not exist
  else if (NULL == parent->next[layers[layer][index]]){

    current = InitNode(ary);
	current->data.push_back(layers[layer-1][index]);
	parent->next[layers[layer][index]] = current;
	
	CreateBranch(current, layers, layer-1, ary, index);
    
  // Add data to existing child
  } 
  else if (NULL != parent->next[layers[layer][index]]){

	current = parent->next[layers[layer][index]]; 
	current->data.push_back(layers[layer-1][index]);
    CreateBranch(current, layers, layer-1, ary, index);
  }
}

// Calculates rank of symbol.
// Input: position of required rank, coded alphabet simbol, MWT root node
// Output: Rank of symbol to position
int Rank(int position, vector<int> symbol, node *search){
  vector<int> next;
  int counter = 0, 
	  current = symbol[0]; // Read first bit of coded symbol

  // Count current symbol in node data
  for (int iter = 0; iter < position; iter++){
	if(current == search->data[iter])
		counter++;
  }
  
  // Return counter if at last bit of the simbol
  if (1 == symbol.size())
	  return counter;
  // Else check next layer (symbol stripped of current bit)
  else
  {
	for (int iter = 1; iter < symbol.size(); iter++)
	  next.push_back(symbol[iter]);
    return Rank(counter, next, search->next[current]);
  }
  
}

// Select function
// Input: rank of required symbol, coded alphabet symbol, MWT root node, top layer
int Select(int rank, vector<int> symbol, node *search, int layer){
  int iter, counter = 0, current = symbol[0], size = search->data.size();
  vector<int> next;

  // Remove current symbol from vector
  for (int iter = 1; iter < symbol.size(); iter++)
	next.push_back(symbol[iter]);

  // If at leaf
  if(layer == 0){
	
	for (iter = 0; iter < size; iter++){
	  if(current == search->data[iter])
		counter++;
	  if(counter == rank)
		return iter+1;
	}
  }
  // If on an intermediary node
  else if(layer > 0 && layer < symbol.size()){

	rank = Select(rank, next, search->next[current], layer-1);
	for (iter = 0; iter < size; iter++){
	  if(current == search->data[iter])
		counter++;
	  if(counter == rank)
		return iter+1;
	}
  }

  // If at root
  else if (layer == symbol.size()){
	
	rank = Select(rank, next, search->next[current], layer-1);
	for(iter = 0; iter < size; iter++){
	  if(current == search->data[iter])
		counter++;
	  if(counter == rank)
		return iter;
	}
  }

}
