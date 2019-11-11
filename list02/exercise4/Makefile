scan: scan.c
	gcc -lfl -o scan scan.c -lm

scan.c: zad4.l
	flex -o scan.c zad4.l

clean:
	rm scan.c scan
