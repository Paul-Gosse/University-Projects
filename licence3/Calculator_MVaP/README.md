# Licence 3 - FantasticAnimals_FrontEndWebsite

| Year        | Place              |
| ----------- | ------------------ |
| 2020 - 2021 | University of Caen |

### Objectif / Objective

Réaliser une calculette qui saurait résoudre des maths tels que soustraction, addition, etc ... Mais également des fonctions plus complexes tels que des fonctions du premier et second degré.

Develop a calculator that could solve maths such as subtraction, addition, etc... But also more complex functions such as first and second degree functions.

### Prérequis / Requirements

- MVaP
- G4
- antlr

### Usage / Usage

```bash
    # You could probably need to export the necessary export
    $ export PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin:/usr/site/bin
    $ export CLASSPATH=.:/usr/share/java/stringtemplate4.jar:/usr/share/java/antlr4.jar:/usr/share/java/antlr4-runtime.jar:/usr/share/java/treelayout.jar

    # You need to compile the Calculette.g4 file
    $ java org.antlr.v4.Tool Calculette.g4
    or
    $ antlr4 Calculette.g4

    # You need to compile the Calculette.java file
    $ javac Calculette*.java

    # You have to start the calculator
    $ java org.antlr.v4.runtime.misc.TestRig Calculette start
    or
    $ antlr4-grun Calculette start

    # MVaP section
    $ java org.antlr.v4.Tool MVaP.g4
    or
    antlr4 MVaP.g4

    #Compile MVaP assembler
    $ javac MVaPAssembler.java CBaP.java

    #To execute
    $ java MVaPAssembler -d add.mvap
    $ java CBaP -d add.mvap.cbap
```
