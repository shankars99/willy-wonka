
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

char *encrypt_affine(char plain_text[], int a, int b)
{
    int text_len = strlen(plain_text);
    char *cipher_text = (char *)malloc(text_len);

    for (int i = 0; i < text_len; i++)
    {
        if (plain_text[i] != ' ')
            cipher_text[i] = toupper(cipher_text[i]) +
                             (char)((((a * (toupper(plain_text[i]) - 'A')) + b) % 26) + 'A');
        else
            cipher_text[i] += plain_text[i];
    }

    return cipher_text;
}

char *decrypt_affine(char cipher_text[], int a, int b)
{

    int text_len = strlen(cipher_text);
    char *plain_text = (char *)malloc(text_len);

    int a_inv = 0;
    int flag = 0;

    for (int i = 0; i < 26; i++)
    {
        flag = (a * i) % 26;

        if (flag == 1)
        {
            a_inv = i;
        }
    }
    for (int i = 0; i < text_len; i++)
    {
        if (cipher_text[i] != ' ')
            plain_text[i] = toupper(plain_text[i]) +
                            (char)(((a_inv * ((toupper(cipher_text[i]) + 'A' - b)) % 26)) + 'A');
        else
            plain_text[i] += cipher_text[i];
    }

    return plain_text;
}