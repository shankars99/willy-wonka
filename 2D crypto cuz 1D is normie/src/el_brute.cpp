//Row Cipher

//ttnaaptmtsuoaodwcoixknlypetz
//attackpostponeduntiltwoamxyz

#include <string>
#include <iostream>
#include <algorithm>
using namespace std;

class Columnar
{
public:
    //key is the column headers for re-arranging ex: 4312
    string key;

    string plain_text;
    string cipher_text;
    string ans_text;
    string input;
    int i, j, option, length, height, key_length;

    //remove this and the first function after the class
    void usage();
    void setMode();
    int getInput();
    bool key_gen();
    string getKey();
    
    void encode(string plain_text, string key);
    string getEncodeChar(size_t loc);
    
    void decode();
    string getDecodeChar(int offset);
    
    void computeDoubleKey();
    void brute_preprocess();
    void display(string, string);
};

void Columnar::usage()
{
    cout << "Usage:\n Input length should be an integer multiple of key length\n The key for brute force is the desired output that is to be matched (i.e. the plaintext)\n\n";
    cout << " Enter your choice:1\n Enter the string:helloworldiamshank\n Enter the key:432561\n OUTPUT: wakllhershomldaoin\n\n";
    cout << " Enter your choice:2\n Enter the string:wakllhershomldaoin\n Enter the key:432561\n OUTPUT: helloworldiamshank\n\n";
    cout << " Enter your choice:3\n Enter the string:ttnaaptmtsuoaodwcoixknlypetz\n Enter the key:attackpostponeduntiltwoamxyz\n OUTPUT:\n Plain text   = attackpostponeduntiltwoamxyz\n Desired text = attackpostponeduntiltwoamxyz\n Key is       = 4312567\n Tries taken  = 2399\n\n";
}

string Columnar :: getKey(){
    string inp;
    cout << "\nEnter the "<<ans_text<<":";
    getline(cin, inp);

    return(inp);
}


void Columnar :: setMode(){
    if(option < 3){
        ans_text = "key";
    }
    else{
        ans_text = "ans_text";
    }
    

}
int Columnar ::getInput()
{
    //usage();
    cout << "1.Encode\n2.Decode\n3.Just brute it\n4.Double Transposition\n Enter your choice:";
    cin >> option;
    getchar();

    cout << "Enter the string:";
    getline(cin, input);

    setMode();
    key = getKey();

    if (option == 1)
    {
        plain_text = input;
    }
    else
    {
        cipher_text = input;
    }

    length = input.length();
    if (option >= 3)
    {
        ans_text = key;
    }
    else
    {
        key_length = key.length();
        height = length / key_length;
    }

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

void Columnar ::encode(string plain_text, string key)
{
    cipher_text = "";
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
   plain_text = "";
    if (option < 3)
    {
        for (j = 0; j < height; ++j)
        {
            plain_text += getDecodeChar(j);
        }
    }
    else
    {
        int count = 0;
        while (key_gen())
        {
            for (j = 0; j < height; ++j)
            {
                plain_text += getDecodeChar(j);
            }
            if (plain_text.compare(ans_text) == 0)
            {
                plain_text  = "\nPlain text   = "  + plain_text;
                plain_text += "\nDesired text = " + ans_text;
                plain_text += "\nKey is       = " + key;
                plain_text += "\nTries taken  = " + to_string(count);

                return;
            }

            count++;
            plain_text = "";
        }
        plain_text = "Un-matchable as the key falls within no permutations of the input text";
    }
}

bool Columnar ::key_gen()
{
    bool flag = next_permutation(key.begin(), key.end());
    return flag;
}

void Columnar::brute_preprocess()
{
    key = "";
    for (i = 1; i < length / 2; i++)
    {
        if (length % i == 0)
        {
            height = i;
            if (key_length == height)
            {
                height = j;
                key = "";
                for (i = 0; i < key_length; i++)
                    key += (i + '1');
                return;
            }
            key_length = length / i;
            j = height;
        }
    }
    height = j;
    for (i = 0; i < key_length; i++)
        key += (i + '1');
}

void Columnar :: computeDoubleKey(){
    plain_text = key;
    display("cipher text round 1", plain_text);

    encode(plain_text, getKey());
    plain_text = cipher_text.substr(0, key.length());
    display("cipher text round 1", plain_text);

    encode(plain_text, getKey());
    cipher_text = cipher_text.substr(0, key.length());
    display("cipher text round 2", plain_text);
}

void Columnar ::display(string output_name, string output)
{
    cout << "\n" + output_name + ": " << output << "\n";
}

int main()
{
    Columnar obj;
    int option = obj.getInput();

    if (option == 1)
    {
        obj.encode(obj.plain_text, obj.key);
        obj.display("cipher text", obj.cipher_text);
    }
    else
    {
        if (option >= 3)
        {
            obj.brute_preprocess();
            if(option == 4){
                obj.option = 1;

                obj.input = obj.ans_text;
                obj.setMode();
                obj.computeDoubleKey();

                obj.ans_text = obj.input;
                obj.option = 4;
            }
        }
        obj.decode();
        obj.display("result", obj.plain_text);
    }

    return (1);
}

/*
Enter the string:1234567
Enter the key:1234567
Enter the key:1762345
Enter the key:3425671
*/