%{
#include <stdio.h>
int yywrap();
int yylex();
int lines = 0, words = 0;
%}

WHITE [ \t]

%%
^{WHITE}*\n*		;
{WHITE}+$			;
{WHITE}+			{printf(" "); words++;}/*kazda ilosc spacji i tabulacji na 1 spacje*/
\n 					{printf("\n"); lines++; words++;}/*kazda nowa linia na nowa linie*/
%%


int yywrap() {
  return 1;
}

int main() {
  yylex();
  printf("\nNumber of lines: %d\n", lines);
  printf("Number of words: %d\n", words);
  return 0;
}
