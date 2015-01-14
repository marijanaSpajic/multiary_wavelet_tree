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
 
// Translates numbered alphabet into coded words (dependatn on arity)
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

// Structure of a MW tree node
typedef struct node {
  node *previous;
  vector<int> data;
  vector<node*> next;  
} node;

// Initializes non-root node
node* InitNode(node &parent, int ary){
	node *current = new node;
    current->previous = NULL;
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
  } else if (NULL != parent->next[layers[layer][index]]){

	  current = parent->next[layers[layer][index]]; 
	  current->data.push_back(layers[layer-1][index]);
      CreateBranch(current, layers, layer-1, ary, index);
  }
	  


}