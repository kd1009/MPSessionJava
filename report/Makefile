.PHONY: main clean
main: main.pdf

main.aux:
	latex main.tex

main.bbl: main.tex main.aux biblio.bib
	bibtex main

main.dvi: main.tex main.aux main.bbl
	latex main.tex && latex main.tex

main.pdf: main.dvi
	dvipdfm main.dvi


clean:
	rm -f main.dvi main.aux main.bbl
