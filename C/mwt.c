#include<stdio.h>
#include<stdlib.h>
#include<math.h>

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

    int * alphabet[128] = {NULL};
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
    int numOfChar = 0;
    while((character = fgetc(file)) != EOF){
            inputStream[i] = character;
            if(alphabet[character]==NULL){
                alphabet[character] = calculateCodeValue(character, arity, treeLayers);
            }
            i++;
            }

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

    fclose(file);
}
