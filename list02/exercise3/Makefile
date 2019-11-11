scan: scan.c
	gcc -lfl -o scan scan.c -w

scan.c: zad3.l
	flex -o scan.c zad3.l

clean:
	rm scan.c scan
