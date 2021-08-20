#include <stdio.h>
#include <netdb.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

#include "caeser.h"
#include "affine.h"
#include "vignere.h"

#define MAX 80
#define PORT 8080
#define SA struct sockaddr

void showOptions()
{
	printf("\n1.Caeser\n2.Affine\n3.Vignere\n");
}

char getMode()
{
	char mode;
	showOptions();
	printf("Enter the mode:");
	scanf("%c", &mode);
	getchar();

	return mode;
}
void chat(int sockfd)
{
	char buff[MAX];
	char buff_key[MAX];

	int n;

	char key1;
	char key2;

	int temp_key1 = 0;
	int temp_key2 = 0;

	char mode = getMode();
	write(sockfd, &mode, sizeof(mode));

	while (1)
	{
		bzero(buff, sizeof(buff));
		printf("\n>>> ");

		n = 0;
		while ((buff[n++] = getchar()) != '\n')
			;
		char *cipher_text;

		switch (mode)
		{
		case '1':
			printf("Enter the key:");
			scanf("%d", &temp_key1);

			key1 = temp_key1;
			cipher_text = encrypt_caesar(buff, temp_key1);

			write(sockfd, cipher_text, strlen(buff) - 1);
			write(sockfd, &key1, sizeof(key1));

			getchar();
			break;

		case '2':
			printf("Enter the first key:");
			scanf("%d", &temp_key1);

			printf("Enter the second key:");
			scanf("%d", &temp_key2);

			key1 = temp_key1;
			key2 = temp_key2;
			cipher_text = encrypt_affine(buff, temp_key1, temp_key2);

			write(sockfd, cipher_text, strlen(buff) - 1);
			write(sockfd, &key1, sizeof(key1));
			write(sockfd, &key2, sizeof(key2));

			getchar();
			break;

		case '3':
			n = 0;
			bzero(buff_key, sizeof(buff_key));
			printf("Enter the key of size %ld:", strlen(buff) - 1);
			while ((buff_key[n++] = getchar()) != '\n')
				;

			cipher_text = encrypt_vignere(buff, buff_key);

			write(sockfd, cipher_text, strlen(buff) - 1);
			write(sockfd, buff_key, strlen(buff) - 1);
			break;

		default:
			printf("Wrong mode number entered");
		}

		bzero(buff, sizeof(buff));
		read(sockfd, buff, sizeof(buff));

		printf("Server: %s\n", buff);
		if ((strncmp(buff, "exit", 4)) == 0)
		{
			printf("~Client Exits~\n");
			break;
		}
	}
}

int main()
{
	int sockfd, connfd, len;
	struct sockaddr_in server, cli;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
	{
		printf("Socket creation failed.\n");
		exit(0);
	}
	printf("Creation Success!\n");

	bzero(&server, sizeof(server));

	server.sin_family = AF_INET;
	server.sin_addr.s_addr = htonl(INADDR_ANY);
	server.sin_port = htons(PORT);

	if ((connect(sockfd, (SA *)&server, sizeof(server))) != 0)
	{
		printf("Socket connection failed.\n");
		exit(0);
	}
	printf("Connection Success!\n");

	chat(sockfd);

	close(sockfd);
}
