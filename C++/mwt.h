#include <iostream>
#include <vector>
#include <assert.h>

using namespace std;

// Translate alphabet letters into ASCII values
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

	reverse(code_word.begin(), code_word.end());
	return code_word;
}
 
// Translates numbered alphabet into coded words (depending on arity)
vector<vector<int>> GenerateCodeAlphabet(vector<int> numbered_alphabet, int ary, int number_of_digits) {
  int iter, quotient, remainder = 0;
  vector<int> code_word;
  vector<vector<int>> coded_words;
  
  
  for(iter = 0; iter < numbered_alphabet.size(); iter++){
/*	quotient = numbered_alphabet[iter];

	int digits = 1;
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

	reverse(code_word.begin(), code_word.end());


	*/
	code_word = WordCoder(numbered_alphabet[iter], ary, number_of_digits);

	coded_words.push_back(code_word);	
	code_word.clear();
  }
  
  return coded_words;

}

// Structure of a MW tree node
typedef struct node {
  node *previous;
  vector<int> data;
  vector<node*> next;  
} node;

// Initializes non-root node
node* InitNode(node parent, int ary){
  node *current = new node;
  current->previous = &parent;
  for(int iter = 0; iter < ary; iter++){
	current->next.push_back(NULL);
  }
  return current;
}

// Initalizes root node
node* InitRoot(int ary){
  node *root = new node;
  for(int iter = 0; iter < ary; iter++){
  root->next.push_back(NULL);
  }
  return root;
}

// Recursive; generates a single MW tree branch (down to the root)
void CreateBranch(node *parent, vector<vector<int>> layers, int layer, int ary, int index){
  node *current;

  // Return if at bottom layer
  if (layer == 0)
	return;

  // Create child if it does not exist
  else if (NULL == parent->next[layers[layer][index]]){

    current = InitNode(*parent, ary);
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
int Rank(int position, vector<int> symbol, node *search){
  vector<int> next;
  int counter = 0, current = symbol[0];

  
  for (int iter = 0; iter < position; iter++){
	if(current == search->data[iter])
		counter++;
  }
  
  if (1 == symbol.size())
	  return counter;
  else
  {
	for (int iter = 1; iter < symbol.size(); iter++)
	next.push_back(symbol[iter]);
    return Rank(counter, next, search->next[current]);
  }
  
}

int Select(int rank, vector<int> symbol, node *search, int layer){
  int iter, counter = 0, current = symbol[0], size = search->data.size();
  vector<int> next;

  for (int iter = 1; iter < symbol.size(); iter++)
	next.push_back(symbol[iter]);

  if(layer == 0){
	
	for (iter = 0; iter < size; iter++){
	  if(current == search->data[iter])
		counter++;
	  if(counter == rank)
		return iter+1;
	}
  }
  else if(layer > 0 && layer < symbol.size()){

	rank = Select(rank, next, search->next[current], layer-1);
	for (iter = 0; iter < size; iter++){
	  if(current == search->data[iter])
		counter++;
	  if(counter == rank)
		return iter+1;
	}
  }
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

/*
  if(layer == 0){
    int current = symbol[0], counter = 0;
	for(int iter = 0; iter < search->data.size(); iter++){
	  if(current == search->data[iter])
		  counter++;
	  if(counter == rank)
		  return iter+1;
	}
  }
/*
  if(layer > 0){
    int num_of_children = search->next.size();
    for (int iter = 0; iter < num_of_children; iter++)
		return get_select(rank, symbol, search->next[iter], layer-1);
  }
  
  */


