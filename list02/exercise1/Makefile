scan: scan.c
	gcc -lfl -o scan scan.c

scan.c: zad1.l
	flex -o scan.c zad1.l

clean:
	rm scan.c scan
