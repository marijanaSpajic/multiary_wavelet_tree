#include<stdio.h>
#include<stdlib.h>
#include<math.h>

typedef struct Node Node;

struct Node {
    int *data;
    Node **child;
    Node *parent;
    int numOfValues;
};

//create node
Node * createTree(char *inputStream, int fileLength, int **alphabet, int arity, int numOfDigits){

    Node* root = (Node *) malloc(sizeof(Node));
    (*root).data = (int *)malloc(fileLength*sizeof(int));
    (*root).data[0] = -1;
    (*root).child = (Node **)malloc(arity*sizeof(Node*));
    (*root).parent = NULL;
    (*root).numOfValues = 0;
    int i;
    for(i = 0; i < arity; i++){
        Node* child = (Node *)malloc(sizeof(Node));
        (*child).data = (int *)malloc(sizeof(int));
        (*child).data[0] = -1;
        (*child).parent = root;
        (*root).child[i] = child;
    }

    //for each character in input stream
    for (i = 0; i < fileLength; i++){
        int c = inputStream[i];
        int *codeValue = alphabet[c];

        //save each digit to appropriate node
        int j;
        Node* temp = root;
        for(j = 0; j < numOfDigits; j++){
            int digit = codeValue[j];
            //put first digit in root
            if(j==0){
                (*root).data[i] = digit;
                (*root).numOfValues = (*root).numOfValues + 1;
            }
            else {
                  Node* node = (*temp).child[codeValue[j-1]];
                    //create child if doesn't exist
                if((*node).data[0] == -1){
                    (*node).data[0] = digit;
                    (*node).numOfValues = 1;
                    (*node).child = (Node **)malloc(arity*sizeof(Node*));
                    (*node).parent = (Node *)malloc(sizeof(Node));
                    int k;
                    for(k = 0; k < arity; k++){
                        Node* children = (Node *)malloc(sizeof(Node));
                        (*children).data = (int *)malloc(sizeof(int));
                        (*children).data[0] = -1;
                        (*children).parent = node;
                        (*node).child[k] = children;
                    }
                    temp = node;
                }
                //child already exists
                else {
                    int size = (*node).numOfValues + 1;
                    (*node).data = (int *) realloc((*temp).child[codeValue[j-1]]->data, size*sizeof(int));
                    (*node).data[size-1] = digit;
                    (*node).numOfValues = (*node).numOfValues + 1;
                    temp = node;
                }
            }
            }
    }
    return root;
}

//convert number to different base; result is code value of alphabet character
int * calculateCodeValue (int value, int base, int numOfDigits){
    int *codeValue = (int *) malloc(numOfDigits*sizeof(int));

    if(base==10 || value==0){
        codeValue[numOfDigits - 1] = value;
    }
    else {
        int result = value;
        int modResult = 0;
        int i = 1;
        while(result != 0){
            modResult = result % base;
            codeValue[numOfDigits - i] = modResult;
            i++;
            result = result/base;
        }
        while(i < (numOfDigits+1)){
            codeValue[numOfDigits - i] = 0;
            i++;
        }
    }

    return codeValue;
}

int main (int argc, char *argv[]){
    if(argc != 3){
        printf("Number of parameters should be 2 (filename, arity).");
        exit(1);
    }

    int *alphabet[128] = {NULL};
    int arity = atoi(argv[2]);
    int treeLayers = ceil(7/log2(arity));
    //printf("Number of layers: %d \n", treeLayers);

    //open file and get file length
    FILE *file;
    file = fopen(argv[1],"r");
    if(file == NULL){
        printf("File couldn't be opened.");
        exit(1);
    }
    fseek(file, 0, SEEK_END);
    int fileLength = ftell(file);
    fseek(file, 0, SEEK_SET);

    //save data from file to array
    char inputStream[fileLength];
    printf("length: %d \n", fileLength);
    int i = 0;
    int character;
    while((character = fgetc(file)) != EOF){
            inputStream[i] = character;
            if(alphabet[character]==NULL){
                alphabet[character] = calculateCodeValue(character, arity, treeLayers);
            }
            i++;
            }
    fclose(file);

    Node * root = createTree(&inputStream[0], fileLength, &alphabet[0], arity, treeLayers);

    printf("Creating tree finished.\n");
    int a = 1;
    while (a) {
        char command;
        int position;
        char charOfAlphabet;
        int rank, select;
        printf("Continue (c) or exit (e)? \n");
        scanf(" %c", &command);
        if (command=='e'){
            a = 0;
            break;
        }
        else if(command=='c'){
            printf("\nType your command.\n\n");
            printf("Characters in the tree:\n");
            for(j = 0; j < 128; j++){
                if(alphabet[j] != NULL){
                    printf("%c ", j);
                }
            }
            printf("\n\n");
            printf("Possible commands: \n \n");
            printf("1) RANK - r 'position' 'character' \t \t MAX_POSITION = %d", fileLength);
            printf("\n \n");
            printf("2) SELECT - s 'num_of_character' 'character' \n \n");
            scanf(" %c %d %c", &command, &position, &charOfAlphabet);
            if(command=='r'){
                if(position > fileLength || position < 0){
                    printf("Wrong value of rank.\n\n");
                }
                else if(alphabet[charOfAlphabet] == NULL){
                    printf("There is no such character in alphabet.\n\n");
                }
                else {
                    int *codeValue = alphabet[charOfAlphabet];
                    //rank
					//printf("Calculated rank: %d", rank);
                }
            }
            else if(command=='s'){
                if(alphabet[charOfAlphabet] == NULL){
                    printf("There is no such character in alphabet.\n\n");
                }
                else {
                    //select
                    //printf("%d. character %c is on position: %d", position, charOfAlphabet, select);
                }
            }
            else {
                printf("Wrong command.\n");
            }
        }
        else {
            printf("Wrong command. Type 'c' for continue or 'e' for exit.\n\n");
        }
    }

return 0;
}
