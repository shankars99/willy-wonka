//Row Cipher

#include <string>
#include <iostream>
using namespace std;

class Columnar
{
public:
    //key is the column headers for re-arranging ex: 4312
    string key;

    string plain_text;
    string cipher_text;
    string input;
    int i, j, option, length, height, key_length;

    //remove this and the first function after the class
    void usage();
    int getInput();
    void encode();
    void decode();
    void display(string);
    string getEncodeChar(size_t loc);
    string getDecodeChar(int offset);
};

void Columnar::usage()
{
    cout << "Usage:\n Input length should be an integer multiple of key length\n";
    cout << " Enter your choice:1\n Enter the string:helloworldiamshank\n Enter the key:432561\n OUTPUT: wakllhershomldaoin\n\n";
    cout << " Enter your choice:2\n Enter the string:wakllhershomldaoin\n Enter the key:432561\n OUTPUT: helloworldiamshank\n\n";
}

int Columnar ::getInput()
{
    usage();
    cout << "1.Encode\n2.Decode\nEnter your choice:";
    cin >> option;
    getchar();

    cout << "Enter the string:";
    getline(cin, input);

    cout << "Enter the key:";
    getline(cin, key);

    if (option == 1)
    {
        plain_text = input;
    }
    else
    {
        cipher_text = input;
    }

    length = input.length();
    key_length = key.length();
    height = length / key_length;

    return (option);
}

string Columnar ::getEncodeChar(size_t loc)
{

    string temp = "";
    /*
        ex: helloworldiamshank becomes for coder 615324

        6 1 5 3 2 4
        -----------
        h e l l o w
        o r l d i a
        m s h a n k

        for run 1, location of 1 in the key is at 1
        so we go (((1)+5)+5)+5... as
        'o' is location '5' characters (length of key) from e and so on.
    */
    for (j = 0; j < length; j += key_length)
    {
        temp += plain_text[loc + j];
    }

    return (temp);
}

void Columnar ::encode()
{
    char ch = '1';
    for (i = 0; i < key_length; ++i)
    {
        //find the location of 1 in the key for execution 1
        size_t loc = key.find(ch);
        cipher_text += getEncodeChar(loc);

        //increase key to 2 for execution 1
        ch += 1;
    }
}

string Columnar ::getDecodeChar(int offset)
{

    string temp = "";
    /*
    ers oin lda wak llh hom
     1   2   3   4   5   6 - blocks

    with key as 615324

    key0 - 6 (convert to integer)
    move to block 6 "hom"
    and offset value of 0 is 'h'

    key1 - 1 (convert to integer)
    move to block 1 "ers"
    and offset value of 0 is 'e'
    ...

    key4 - 3 at offset = 2
    move to block 3
    and offset value of 2 is 'a'
    */

    for (i = 0; i < key_length; ++i)
    {
        temp += cipher_text[height * (key[i] - '1') + offset];
    }
    return temp;
}

void Columnar ::decode()
{
    /*
    iterate through the number of blocks formed (the height)
    ers oin lda wak llh hom
    */

    for (j = 0; j < height; ++j)
    {
        plain_text += getDecodeChar(j);
    }
}

void Columnar ::display(string output)
{
    cout << "OUTPUT: " << output << "\n";
}

int main()
{
    Columnar obj;
    int option = obj.getInput();

    if (option == 1)
    {
        obj.encode();
        obj.display(obj.cipher_text);
    }
    else
    {
        obj.decode();
        obj.display(obj.plain_text);
    }

    return (1);
}