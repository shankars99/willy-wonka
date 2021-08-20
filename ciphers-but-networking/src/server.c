#include <stdio.h>
#include <netdb.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>
#include <ctype.h>

#include "caeser.h"
#include "affine.h"
#include "vignere.h"

#define MAX 80
#define PORT 8080
#define SA struct sockaddr

void chat(int sockfd)
{
	char buff[MAX];
	char buff_key[MAX];

	int n;
	char mode;

	int key1 = 0;
	char temp_key1;

	int key2 = 0;
	char temp_key2;

	read(sockfd, &mode, sizeof(mode));
	printf("mode = %c\n", mode);
	while (1)
	{
		bzero(buff, MAX);
		read(sockfd, buff, sizeof(buff));

		printf("\nClient(Encrypted): %s ", buff);
		char *plain_text;

		switch (mode)
		{
		case '1':
			read(sockfd, &temp_key1, sizeof(temp_key1));
			key1 = temp_key1;

			plain_text = decrypt_caeser(buff, key1);
			break;

		case '2':
			read(sockfd, &temp_key1, sizeof(temp_key1));
			read(sockfd, &temp_key2, sizeof(temp_key2));
			key1 = temp_key1;
			key2 = temp_key2;

			plain_text = decrypt_affine(buff, key1, key2);
			break;

		case '3':
			bzero(buff_key, MAX);
			read(sockfd, buff_key, sizeof(buff_key));

			plain_text = decrypt_vignere(buff, buff_key);
			break;

		default:
			printf("%d", mode);
		}
		printf("\nClient(Decrypted): %s\n>>> ", plain_text);

		bzero(buff, MAX);
		n = 0;
		while ((buff[n++] = getchar()) != '\n')
			;
		write(sockfd, buff, sizeof(buff));

		if (strncmp("exit", buff, 4) == 0)
		{
			printf("~Server Exits~\n");
			break;
		}
	}
}

int main()
{
	int sockfd, connfd, len;
	struct sockaddr_in servaddr, cli;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
	{
		printf("Socket creation failed.\n");
		exit(0);
	}
	printf("Creation Success!\n");

	bzero(&servaddr, sizeof(servaddr));

	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servaddr.sin_port = htons(PORT);

	if ((bind(sockfd, (SA *)&servaddr, sizeof(servaddr))) != 0)
	{
		printf("Socket binding failed.\n");
		exit(0);
	}
	printf("Binding Success!\n");

	if ((listen(sockfd, 5)) != 0)
	{
		printf("Listen Failed \n");
		exit(0);
	}
	printf("Listen Success!\n");

	len = sizeof(cli);

	connfd = accept(sockfd, (SA *)&cli, &len);
	if (connfd < 0)
	{
		printf("Server accept failed.\n");
		exit(0);
	}
	else
	{
		printf("Accept Success!\n");
	}

	chat(connfd);
	close(sockfd);
}
