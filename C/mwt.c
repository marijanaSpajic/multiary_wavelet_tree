#include<stdio.h>
#include<stdlib.h>
#include<math.h>

typedef struct Node Node;

struct Node {
    int *data;
    Node **child;
    Node *next;
    int numOfValues;
};

//create node
Node * createTree(char *inputStream, int fileLength, int **alphabet, int arity, int numOfDigits){

    Node* root = (Node *) malloc(sizeof(Node));
    (*root).data = (int *)malloc(fileLength*sizeof(int));
    (*root).data[0] = -1;
    (*root).child = (Node **)malloc(arity*sizeof(Node*));
    (*root).next = NULL;
    (*root).numOfValues = 0;
    int i;
    for(i = 0; i < arity; i++){
        Node* child = (Node *)malloc(sizeof(Node));
        (*child).data = (int *)malloc(sizeof(int));
        (*child).data[0] = -1;
        (*root).child[i] = child;
    }

    //for each character in input stream
    for (i = 0; i < fileLength; i++){
        int c = inputStream[i];
        printf("%d \n", c);
        int *codeValue = alphabet[c];

        //save each digit to appropriate node
        int j;
        Node* temp = root;
        for(j = 0; j < numOfDigits; j++){
            int digit = codeValue[j];
            //put first digit in root
            if(j==0){
                (*root).data[i] = digit;
                printf("ROOT: %d \n", (*root).data[i]);
                (*root).numOfValues = (*root).numOfValues + 1;
            }
            else {
                  Node* node = (*temp).child[codeValue[j-1]];
                  printf("test: %d \n", (*node).data[0]);
                    //create child if doesn't exist
                if((*node).data[0] == -1){
                    (*node).data[0] = digit;
                printf("CHILD: %d \n", (*node).data[0]);
                    (*node).numOfValues = 1;
                    (*node).child = (Node **)malloc(arity*sizeof(Node*));
                    (*node).next = (Node *)malloc(sizeof(Node));
                    int k;
                    for(k = 0; k < arity; k++){
                        Node* children = (Node *)malloc(sizeof(Node));
                        (*children).data = (int *)malloc(sizeof(int));
                        (*children).data[0] = -1;
                        (*node).child[k] = children;
                        printf("dijete: %d \n", k);
                    }
                    if(digit < (arity-1)) {
                        int valueOfNext = codeValue[j-1] + 1;
                        (*temp).child[codeValue[j-1]]->next = (*temp).child[valueOfNext];
                    }
                    else {
                        (*temp).child[codeValue[j-1]]->next = NULL;
                    }
                    temp = node;
                }
                //child already exists
                else {
                    int size = (*node).numOfValues + 1;
                    (*node).data = (int *) realloc((*temp).child[codeValue[j-1]]->data, size*sizeof(int));
                    (*node).data[size-1] = digit;
                    printf("CHILD NODE EXISTS: %d \n", (*node).data[size-1]);
                    (*node).numOfValues = (*node).numOfValues + 1;
                    temp = node;
                }
            }
            }
           // printf("kraj");
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
    //printf("%p\n", *alphabet);
    int arity = atoi(argv[2]);
    int treeLayers = ceil(7/log2(arity));
    printf("Number of layers: %d \n", treeLayers);

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

    //test code values of alphabet
    int j;
    int k = 0;
    for (j = 0; j < 128; j++){
        if(alphabet[j] != NULL){
            for(k = 0; k < treeLayers; k++){
                printf("%d", alphabet[j][k]);
            }
        printf("\n");
        }
    }

   Node * root = createTree(&inputStream[0], fileLength, &alphabet[0], arity, treeLayers);

return 0;
}
