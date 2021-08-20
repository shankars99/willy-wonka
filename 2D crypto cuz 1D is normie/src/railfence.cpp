//Railfence

#include <string>
#include <iostream>
#include <cmath>

using namespace std;

/*
helloworldiamshank

h   o   l   m   n
 e l w r d a s a k
  l   o   i   h

  => holmnelwrdasakloih
*/


class Railfence
{
public:
    string plain_text;
    string cipher_text;
    string input;

    //jump_val[4] is the shifting matrix used for decoding to calculate the jump values
    int i, j, option, length, jump_val[4] = {0,0,0,0};

    //remove this and the first function after the class
    void usage();
    int getInput();
    void encode();
    void decode();
    void display(string);
    string getEncodeChar(int index, int shift);
    void getDecodeChar();
};

void Railfence::usage(){
    cout<<"Usage:\n";
    cout << " Enter your choice:1\n Enter the string:helloworldiamshank\n OUTPUT: holmnelwrdasakloih\n\n";
    cout << " Enter your choice:2\n Enter the string:holmnelwrdasakloih\n OUTPUT: helloworldiamshank\n\n";
}
int Railfence ::getInput()
{
    usage();

    cout << "1.Encode\n2.Decode\nEnter your choice:";
    cin >> option;
    getchar();

    cout << "Enter the string:";
    getline(cin, input);


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

string Railfence ::getEncodeChar(int index, int shift)
{

    string temp = "";

    /*
    length-index is used to ignore the values before,
    row 2 starts from char2 and row 3 starts from char3.
    as we are offsetting by that value there is a chance that it overshoots
    since what is added needs to be reduced to maintain the same number of characters in the string
    */
    for (j = 0; j < length - index; j += shift)
    {
        temp += plain_text[index + j];
    }

    return (temp);
}

void Railfence ::encode()
{
    /*
        the encoding for row 1 is h->o->l->... pattern here is (((0)+4)+4)+4...
        the encoding for row 2 is e->l->w->... pattern here is (((0)+2)+2)+2...
        this is a constant jump value that can be observed from the zig-zag pattern
    */
    cipher_text += getEncodeChar(0,4);
    cipher_text += getEncodeChar(1,2);
    cipher_text += getEncodeChar(2,4);
}

void Railfence ::getDecodeChar()
{

    /*
    now on the next iteration we need to add 1 to row 1 as we are ahead by 1 from the prev, and remove 1
    from row2 as we are behind, the same mirrors on r3 and r4.
    */
    jump_val[0] += 1;
    jump_val[2] += 1;

    jump_val[1] -= 1;
    jump_val[3] -= 1;
}

void Railfence ::decode()
{
    /*
      h   o   l
       e l w r d
        l   o

     h o l e l w r d l o , length = 10
     1 2 3 4 5 6 7 8 9 10

     write down the holelwrdlo and trace the jumps



     the jump from char1 'h' to the next 'e' (char 4) is the the number of characters in row1
                                            (i.e. ceil(length/4) = 10/4 = 2.5 = 3) => 1+3 = 4

     the jump from char4 'e' to 'l' (char9) is the number of characters in row2
     (since we're ignoring the first character in row1, the effective length is length-1)
                                            (9/2 = 4.5 = 5) => 4+5 = 9

     and now since we're going up the ladder instead of down it's essentially going backward
     the jump from char9 'l' to 'l' (char5) is the number of characters in [ row2-1 ] since
     we are ignoring the prev character (e at location char4)

    and then the same moving back from char5 to char2 except the -1 adds with the character ignored(+1) and results in 0
    which is basically [ -row1 ] = -3
    */

    jump_val[0] = ceil(length/4.0);
    jump_val[1] = ceil( (length-1) / 2.0);
    jump_val[2] = -(jump_val[1]-1);
    jump_val[3] = -(jump_val[0]);

    j = 0;

    for( i = 0; i<length; ++i){
        plain_text += cipher_text[j];

        if( i%4 == 0 && i>0){
            getDecodeChar();
        }

        j += jump_val[i%4];
    }
}

void Railfence ::display(string output)
{
    cout <<"OUTPUT: "<<output << "\n";
}

int main()
{
    Railfence obj;
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