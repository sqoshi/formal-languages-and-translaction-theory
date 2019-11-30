make: trans_inftopost.l trans_inftopost.y
	bison -d trans_inftopost.y
	flex -l trans_inftopost.l
	g++ -w -std=c++11 lex.yy.c trans_inftopost.tab.c -o calculator 
