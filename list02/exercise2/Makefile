scan: scan.c
	gcc -lfl -o scan scan.c

scan.c: zad2.l
	flex -o scan.c zad2.l

clean:
	rm scan.c scan
