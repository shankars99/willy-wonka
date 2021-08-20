#include <iostream>
#include <cstdio>
#include <map>
#include <string>

using namespace std;

//converting the char to integer
int char_to_num(char ch)
{
  return ((int)ch - 65);
}

string remove_spaces(string str)
{
  int i;
  for (i = 0; i < str.size(); i++)
  {
    if (str[i] == ' ')
    {
      str.erase(str.begin() + i);
      i--;
    }
  }
  return str;
}

//counting the bigrams for question b and c
void count_bigrams(string str)
{
  //creating a map and a interator map
  map<string, int> mp;
  map<string, int>::iterator mp_itr;
  string temp_map;

  int i;

  //converting the string into a map for processing of char - frequency relation
  for (i = 1; i < str.size(); i++)
  {
    temp_map.clear();
    temp_map.push_back(str[i - 1]);
    temp_map.push_back(str[i]);

    if (mp.find(temp_map) == mp.end())
      mp.insert(pair<string, int>(temp_map, 1));
    else
      mp[temp_map]++;
  }

  cout << "1 b. Repeated bigrams\n";

  //checking the map with the char - frequency
  for (mp_itr = mp.begin(); mp_itr != mp.end(); mp_itr++)
  {
    //if the frequency is greater than 1 then we have a bigram
    if ((mp_itr->second) > 1)
      cout << mp_itr->first << ":" << mp_itr->second << endl;
  }

  cout << "\n1 c. Positions of repeated bigrams\n";
  for (i = 1; i < str.size(); i++)
  {
    temp_map.clear();
    temp_map.push_back(str[i - 1]);
    temp_map.push_back(str[i]);

    //parse through the map and if we have a bigram then print the value
    if (mp[temp_map] > 1)
    {
      cout << str[i - 1] << str[i];
      i++;
    }
    else
      cout << "-";
  }
  cout << "\n\n";
}

int main()
{
  string input;
  string token;
  string output;
  int choice;

  cout << "Enter the following:\nInput String :";
  getline(cin, input);

  cout << "Token String :";
  getline(cin, token);

  //cleaning up the spaces
  input = remove_spaces(input);
  token = remove_spaces(token);

  //input val
  int i = 0;
  int i_val;

  //token val
  int t = 0;
  int t_val;

  while (i < input.size())
  {
    if (input[i] == ' ')
      continue;

    i_val = char_to_num(input[i]);
    t_val = char_to_num(token[t]);

    //vignere cipher
    i_val = i_val + t_val;
    i_val = i_val%26 + 65;

    output.push_back((char)i_val);
    t = (t + 1) % token.size();
    i++;
  }

  cout << "1 a. Vigenere Cipher Encryption : \n";
  cout << "Input got from the user : " << input << "\n";
  cout << "Token got from the user : " << token << "\n";
  cout << "VIgnere Encryption      : " << output << "\n\n";

  count_bigrams(output);
}
