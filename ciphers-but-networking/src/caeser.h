#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

char *encrypt_caesar(char plain_text[], int key)
{

    int text_len = strlen(plain_text);
    char *cipher_text = (char *)malloc(text_len);
    strncpy(cipher_text, plain_text, text_len);

    int i;
    for (i = 0; i < text_len; i++)
    {
        cipher_text[i] = toupper(plain_text[i]) + key % 26;
    }

    cipher_text[text_len] = '\0';

    return (char *)cipher_text;
}

char *decrypt_caeser(char cipher_text[], int key)
{

    int text_len = strlen(cipher_text);
    char *plain_text = (char *)malloc(text_len);
    strncpy(plain_text, cipher_text, text_len);

    int i;
    for (i = 0; i < text_len; i++)
    {
        plain_text[i] = toupper(cipher_text[i]) - key % 26;
    }

    plain_text[text_len] = '\0';

    return (char *)plain_text;
}
