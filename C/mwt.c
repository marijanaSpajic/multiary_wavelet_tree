#include<stdio.h>
#include<stdlib.h>
#include<math.h>

int main (int argc, char *argv[]){
    if(argc != 3){
        printf("Number of parameters should be 2 (filename, arity).");
        exit(1);
    }

    int alphabet[128] = {0};
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
            if(alphabet[character]==0){
                alphabet[character]=1;
                numOfChar++;
            }
            i++;
            }

    char charOfAlphabet[numOfChar];
    int j;
    int k = 0;
    for (j = 0; j < 128; j++){
        if(alphabet[j]==1){
            charOfAlphabet[k] = j;
            k++;
        }
    }
    //for(j=0; j < fileLength; j++){
      //  int a = inputStream[j];
        //printf("Znak: %d \n", a);
    //}

    fclose(file);
}
