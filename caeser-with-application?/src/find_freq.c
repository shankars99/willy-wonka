#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#include "caeser.h"

void main(){
    char plain_text[100];
    char twoD[2][100];
    char theeD[3][100];

    int key;
    int i = 0;
    int ctr = 0;

    printf("Enter the plain text:");
    while ((plain_text[i++] = getchar()) != '\n');

    printf("Enter the key:");
    scanf("%d", &key);
    char *cipher_text = encrypt_caesar(plain_text, key);

    int arr[26] = {0};





    for (i = 0; '\0' != cipher_text[i]; i++)
    {
        arr[cipher_text[i] - 'A']++;
    }
    for (i = 0; i < 26; i++)
    {
        if (arr[i] != 0)
            printf("\n%c - %d", i + 'A', arr[i]);
    }






    int j = 0;
    for (i = 0; '\0' != cipher_text[i+1]; i++)
    {
        int flag = 0;
        for(j = 0; j<ctr;j++){
            if (twoD[0][j] == cipher_text[i] && twoD[1][j] == cipher_text[i + 1]){
                flag = 1;
            }
        }
        if(flag == 0){
            twoD[0][i] = cipher_text[i];
            twoD[1][i] = cipher_text[i+1];
             ctr+=1;
        }else{
            ctr--;
        }
    }

    for (i = 0; i < 26; i++)
    {
        arr[i] = 0;
    }

    for (i = 0; '\0' != cipher_text[i + 2]; i++)
    {
        for (j = 0; j < ctr; j++)
        {
            if(twoD[0][j] == cipher_text[i] && twoD[1][j] == cipher_text[i+1]){
                arr[j]++;
            }
        }
    }

    for (i = 0; i < ctr; i++)
    {
        printf("\n%c%c - %d", twoD[0][i], twoD[1][i], arr[i]);
    }
















    ctr = 0;
    j = 0;
    for (i = 0; '\0' != cipher_text[i + 2]; i++)
    {
        int flag = 0;
        for (j = 0; j < ctr; j++)
        {
            if (theeD[0][j] == cipher_text[i] && theeD[1][j] == cipher_text[i + 1] && theeD[2][j] == cipher_text[i + 2])
            {
                flag = 1;
            }
        }
        if (flag == 0)
        {
            theeD[0][i] = cipher_text[i];
            theeD[1][i] = cipher_text[i + 1];
            theeD[2][i] = cipher_text[i + 2];
            ctr += 1;
        }
        else
        {
            ctr--;
        }
    }

    for (i = 0; i < 26; i++)
    {
        arr[i] = 0;
    }

    for (i = 0; '\0' != cipher_text[i + 2]; i++)
    {
        for (j = 0; j < ctr; j++)
        {
            if (theeD[0][j] == cipher_text[i] && theeD[1][j] == cipher_text[i + 1] && theeD[2][j] == cipher_text[i + 2])
            {
                arr[j]++;
            }
        }
    }

    for (i = 0; i < ctr; i++)
    {
        printf("\n%c%c%c - %d", theeD[0][i], theeD[1][i], theeD[2][j], arr[i]);
    }
}

