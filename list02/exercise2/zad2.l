%{
#include <stdio.h>

int yylex();
int yywrap();
%}

%x TAG
%x DOIT
%x CHECK
WHITE [ \t]
%%
\<      {ECHO;BEGIN(TAG);}
        <TAG>{

            ([::blank::]).*		ECHO;
            .                   ECHO;
            \n                  ECHO;
            .>                  {ECHO;printf("\n");BEGIN(DOIT);}
        }
\<!--   BEGIN(DOIT);
                <DOIT>{
                    \<[^\-]*\/.\>           {printf("%s\n",yytext);}
                    .|\n                ;
                    \-\-\>              {BEGIN(INITIAL);}
                }
\<!--.*[\-]{2}.*--\> {printf("%s\n",yytext);}

%%

int yywrap() {
    return 1;
}

int main() {
    return yylex();
}
