//Product Cipher
//https://www.nku.edu/~christensen/092hnr304%20ADFGVX.pdf

#include <string>
#include <iostream>
using namespace std;

class ADFGVX
{
public:
    //got from arranging the values of the 6x6 grind sequentially
    string grid_val = "ai2o0d1bh6mstnwcq4lg7vyrf5e3x29pjk8u";
    //grid row and col names
    string grid_label;

    string plain_text;
    string cipher_text;
    string input;
    int i, option, length;

    //remove this and the first function after the class
    void usage();
    int getInput();
    void encode();
    void decode();
    void display(string);
    string getEncodeChar(char ch);
    string getDecodeChar(char row, char col);
};

void ADFGVX ::usage()
{
    cout << "Usage:\n";
    cout << " Enter your choice:1\n Enter the string:hello 123\n Enter the 6 digit grid labels in caps:EFRTYA\n OUTPUT: FRYRTETEET FEERYT";
    cout << "\n\n Enter your choice:2\n Enter the string:FRYRTETEET FEERYT\n Enter the 6 digit grid labels in caps:EFRTYA\n OUTPUT: hello 123\n\n";
}

int ADFGVX ::getInput()
{

    usage();
    cout << "1.Encode\n2.Decode\nEnter your choice:";
    cin >> option;
    getchar();

    cout << "Enter the string:";
    getline(cin, input);

    cout << "Enter the 6 digit grid labels in caps:";
    getline(cin, grid_label);

    if (option == 1)
    {
        plain_text = input;
    }
    else
    {
        cipher_text = input;
    }

    length = input.length();

    return (option);
}

string ADFGVX ::getEncodeChar(char ch)
{
    size_t loc = grid_val.find(ch);

    string temp = "";

    //get row label value and then column label value for the character
    temp += grid_label[loc / 6];
    temp += grid_label[loc % 6];

    return temp;
}

void ADFGVX ::encode()
{
    for (i = 0; i < length; ++i)
    {
        if (plain_text[i] == ' ')
        {
            cipher_text += ' ';
        }
        else
        {
            cipher_text += getEncodeChar(plain_text[i]);
        }
    }
}

string ADFGVX ::getDecodeChar(char row, char col)
{

    string temp = "";

    //getting the row and column indices
    size_t row_loc = grid_label.find(row);
    size_t col_loc = grid_label.find(col);

    //getting the value at the located indices
    temp += grid_val[row_loc * 6 + col_loc];

    return temp;
}

void ADFGVX ::decode()
{
    for (i = 0; i <= length; i += 2)
    {
        if (cipher_text[i] == ' ')
        {
            plain_text += ' ';
            i++;
        }

        plain_text += getDecodeChar(cipher_text[i], cipher_text[i + 1]);
    }
}

void ADFGVX ::display(string output)
{
    cout << "OUTPUT: " << output << "\n";
}

int main()
{
    ADFGVX obj;
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