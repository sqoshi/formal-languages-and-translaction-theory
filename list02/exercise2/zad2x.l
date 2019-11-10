%{
#include <stdio.h>
#include <string.h>
int yywrap();
int yylex();
%}
start \<\!\-\-
end  \-\-\>
mid \-
%%
{start}[^\-\-]*{end} ;
{start}[^\-]+[\-]{1}[^\-]+{end} ;
%%


int yywrap() {
  return 1;
}

int main() {
  yylex();
  return 0;
}
