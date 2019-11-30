%option noyywrap
%option yylineno

%{
#include <iostream>
#include <cstdlib>
#include "trans_inftopost.tab.h"

#define YY_DECL extern int yylex()

%} 

%x COMMENT

%%
^#.*[\\\n]?                     BEGIN(COMMENT);
\\\n                            ;
[ \t]+                          ;
<COMMENT>{
    \\\n                        ;  
    .*                          ;  
    \n                          BEGIN(INITIAL);
}
[0-9]+                          { yylval = atoi(yytext); return NUMBER; }
\n                              return N_LINE;
\+                              return ADD;
\-                              return SUB;
\*                              return MULT;
\/                              return DIV;
\%                              return MOD;
\^                              return POW;
\(                              return L_BRACKET;
\)                              return R_BRACKET;
.                               return ERR;
%%