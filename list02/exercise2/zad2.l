%{
#include <stdio.h>

int yylex();
int yywrap();
%}

%x TAG
%x DOIT

%%
\<!--   BEGIN(DOIT);
        <DOIT>{
            .|\n                 ;
            \<[^\-]*\>           {printf("%s",yytext);}
            \-\-\>               {BEGIN(INITIAL);}
        }
\<!--.*[\-]{2}.*--\> {printf("%s\n",yytext);}

\<      {ECHO; BEGIN(TAG);}
        <TAG>{
            ([::blank::]).*		ECHO;
            .                   ECHO;
            \n                  ECHO;
            .>                  {ECHO; BEGIN(INITIAL);}
        }

%%

int yywrap() {
    return 1;
}

int main() {
    return yylex();
}
