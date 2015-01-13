#include<stdio.h>
#include<stdlib.h>

int main (int argc, char *argv[]){
    if(argc != 3){
        printf("Number of parameters should be 2 (filename, cardinality).");
        exit(1);
    }

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
    //printf("length: %d \n", fileLength);
    int i = 0;
    int character;
    while((character = fgetc(file)) != EOF){
            inputStream[i] = character;
            i++;
            }
    /*int j;
    for(j=0; j < fileLength; j++){
        char a = inputStream[j];
        printf("Znak: %c \n", a);
    }*/

    fclose(file);
}
