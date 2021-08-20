#include <iostream>
#include <string>
#include <vector>
#include <climits>
using namespace std;

//converting the char to int and indexexing from 0 by removing 65
int to_num(char c)
{
    return ((int)c - 65);
}

string decrypt(string cipher_text, int key)
{
    string plain_text;

    int i = 0;
    int val = 0;

    //iterating through the cipher text
    while (i < cipher_text.size())
    {
        if (cipher_text[i] == ' ') //if blank append to map
        {
            i++;
            plain_text.push_back(' ');
            continue;
        }
        //getting the offset value and then "decoding" aka going backwards in the alphabetical order
        val = to_num(cipher_text[i]);
        val = val - key;

        //A-4 => 0-4 => -4, fixing this as cycling chains can use modulus or an addition of the max value
        if (val < 0)
        {
            val += 26;
        }
        //converting back to characters
        val += 65;
        plain_text.push_back((char)val);
        i++;
    }
    return plain_text;
}

void print_caeser(int key)
{
    //printing the encode-decode table
    
    //printing the plain text version
    char ch = 'a';
    cout << "\nPlain text :";
    for (ch = 'a'; ch <= 'z'; ch++)
    {
        cout << ch << " ";
    }

    //printing the encoded version
    ch = 'A' + key;
    cout <<"\n"<< "Cipher text:";
    for (; ch < 'Z'; ch++)
        cout << ch << " ";
    for (ch = 'A'; ch < ('A' + key); ch++)
        cout << ch << " ";
    cout << "\n";
}

void frequency(string input, int key)
{
    int arr[26];
    int i;
    for (i = 0; i < 26; i++){
        arr[i] = 0;
    }

    int value;
    for (i = 0; i < input.size(); i++)
    {
        if (input[i] == ' ')
            continue;

        value = (int)(input[i] - 65);
        arr[value]++;
    }

    //finding the max and min cipher characters and appending to a map
    cout << "\n";
    int max = INT_MIN;
    int min = INT_MAX;

    char max_cipher;
    char min_cipher;

    for (i = 0; i < 26; i++)
    {
        if (arr[i] == 0)
            continue;
        if (arr[i] > max)
        {
            max = arr[i];
            max_cipher = (char)(i + 65);
        }
        if (arr[i] < min)
        {
            min = arr[i];
            min_cipher = (char)(i + 65);
        }
    }

    string max_plain;
    string min_plain;

    max_plain.push_back(max_cipher);
    min_plain.push_back(min_cipher);

    max_plain = decrypt(max_plain, 3);
    min_plain = decrypt(min_plain, 3);

    cout << "Highest Frequency Cipher Letter is:" << max_cipher << "\n   it's plaintext representation is:" << max_plain << "\n";
    cout << "\nLowest Frequency Cipher Letter is:" << min_cipher << "\n   it's plaintext representation is:" << min_plain << "\n";
}

void maxfreq(string input, int key)
{

    cout << "Encrypted Text:" << input << "\n";

    int arr[26];
    int i;
    for (i = 0; i < 26; i++)
        arr[i] = 0;

    int value;
    for (i = 0; i < input.size(); i++)
    {
        if (input[i] == ' ')
            continue;

        value = (int)(input[i] - 65);
        arr[value]++;
    }

    //find the first, second and 3rd highest frequency characters
    int max = INT_MIN;
    char max_cipher;
    char max1, max2, max3;
    int index;

    for (i = 0; i < 26; i++)
    {
        if (arr[i] == 0)
            continue;
        if (arr[i] > max)
        {
            max = arr[i];
            max_cipher = (char)(i + 65);
            index = i;
        }
    }

    max1 = max_cipher;
    arr[index] = 0;

    max = INT_MIN;

    for (i = 0; i < 26; i++)
    {
        if (arr[i] == 0)
            continue;
        if (arr[i] > max)
        {
            max = arr[i];
            max_cipher = (char)(i + 65);
            index = i;
        }
    }

    max = INT_MIN;
    max2 = max_cipher;
    arr[index] = 0;

    for (i = 0; i < 26; i++)
    {
        if (arr[i] == 0)
            continue;
        if (arr[i] > max)
        {
            max = arr[i];
            max_cipher = (char)(i + 65);
            index = i;
        }
    }

    max3 = max_cipher;
    arr[index] = 0;

    string max_plain1, max_plain2, max_plain3;
    max_plain1.push_back(max1);
    max_plain2.push_back(max2);
    max_plain3.push_back(max3);

    max_plain1 = decrypt(max_plain1, 3);
    max_plain2 = decrypt(max_plain2, 3);
    max_plain3 = decrypt(max_plain3, 3);

    cout<<"\nUsing '-' to denote blank spaces over ' ' to make viewability easy\n";
    cout << "Three most frequent Cipher Text Version" << "\n";

    for (i = 0; i < input.size(); i++)
    {
        if ((input[i] == max1) || (input[i] == max2) || (input[i] == max3) || (input[i] == ' '))
            cout << input[i];
        else
            cout << "-";
    }

    cout << "\n";
    cout << "Three most frequent Plain Text Version" << "\n";

    for (i = 0; i < input.size(); i++)
    {
        if (input[i] == ' ')
        {
            cout << ' ';
            continue;
        }

        if (input[i] == max1)
        {
            cout << max_plain1;
            continue;
        }

        if (input[i] == max2)
        {
            cout << max_plain2;
            continue;
        }

        if (input[i] == max3)
        {
            cout << max_plain3;
            continue;
        }

        cout << "-";
    }
}

int main()
{

    cout << "Q1 a)" << "\n";

    string input = "FDHVDU LQYWHQWHG WKLV FRGH";
    int key;
    cout << "Encrypted Text:" << input << "\n";
    string output;
    output = decrypt(input, 3);
    cout << "Decrypted Text:" << output<< "\n\n";


    cout << "\nQ1 b)\n";

    cout << "New Cipher-Plaintext Combination" << "\n";
    print_caeser(4);
    cout << "\n";
    output = decrypt(input, 4);
    cout << "Encrypted Text:" << input;
    cout << "\nDecrypted Text:" << output << "\n\n";

    cout << "\nQ1 c)";

    frequency(input, 3);
    cout << "\n\n";

    cout << "Q1 d)\n";
    maxfreq(input, 3);
    cout << "\n\n";
    return 0;
}