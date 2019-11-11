%{
  #include <stdio.h>
  int printNum = 0;
%}

%%
"\""([^"]|[\r\n])*"\"" {fprintf(yyout, "%s", yytext);}
"<![CDATA["([^]]|]][^>]|[\r\n])*"]]>" {fprintf(yyout, "%s", yytext);}
"<!--"([^-]|-[^-]|[\r\n])*"-->"  {printf("\n%d", printNum); printf("%s", yytext);  printNum++;}
%%

int yywrap() {
  return 1;
}

int main() {
  extern FILE *yyin, *yyout;

  yyin = fopen("test.xml", "r");
  yyout = fopen("output.txt", "w");

  yylex();

  return 0;
}
