%{
#include <iostream>
#include <string>
#include <cmath>
#include <string.h>

using namespace std;//thats a big big problem

int yylex(void);
void yyerror(char *s);
extern int yylineno;

int errline=0;
string result;
bool err = false;
%}

%token ERR
%token N_LINE
%token L_BRACKET
%token R_BRACKET
%token NUMBER
%left ADD SUB
%left MULT MOD DIV
%right NEG
%right POW


%%
input:  %empty
        | input line                {errline = yylineno;}
;

line:   N_LINE
        | exp N_LINE                {
                                        if(!err){
                                            cout << result << "\t= " << $1 << endl;
                                        }
                                        else {
                                            err = false;
                                        }
                                        result = "";
                                    }
        | ERR N_LINE                {
                                        errline = yylineno;
                                        yyerror("syntax error");
                                    }
        | error N_LINE              ;
;

exp:    NUMBER                      {
                                        result.append(to_string($1).append(" "));
                                        $$ = $1;
                                    }
        | exp ADD exp               {
                                        result.append("+ ");
                                        $$ = $1 + $3;
                                    }
        | exp SUB exp               {
                                        result.append("- ");
                                        $$ = $1 - $3;
                                    }
        | exp MULT exp              {
                                        result.append("* ");
                                        $$ = $1 * $3;
                                    }
        | exp DIV exp               {
                                        result.append("/ ");
                                        if($3 == 0) {
                                            err = true;
                                            errline = yylineno;
                                            yyerror("dividing by zero not allowed");
                                        }
                                        else {
                                            $$ = floor((double)$1 / (double)$3);
                                        }
                                    }
        | exp MOD exp               {
                                        result.append("% ");
                                        if($3 == 0) {
                                            err = true;
                                            errline = yylineno;
                                            yyerror("dividing by zero not allowed");
                                        }
                                        else {
                                            $$ = $1 - floor((double)$1 / (double)$3) * $3;
                                        }
                                    }
        | exp POW exp               {
                                        result.append("^ ");
                                        if($3 < 0) {
                                            err = true;
                                            errline = yylineno;
                                            yyerror(" negative exponent not allowed");
                                        }
                                        else {
                                            $$ = pow($1, $3);
                                        }
                                    }
        | SUB exp %prec NEG         {
                                        result.append("~ ");
                                        $$ = -$2;
                                    }
        | L_BRACKET exp R_BRACKET   {
                                        $$ = $2;
                                    }
;
%%

void yyerror(char *s) {

    cerr << "In line " << (errline<=0 ? 0 : errline-1) << ": " << string(s) << "." << endl;
    result = "";
    return;
}

int main() {
    return yyparse();
}
