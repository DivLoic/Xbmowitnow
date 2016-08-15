# MowItNow
###### Xebia technical test - Loïc DIVAD

This project is an answer to the technical test MowitNow.
> La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires.        
> ...       
> Concevoir et écrire un programme s'exécutant sur une JVM ≥ 1.7 ou un serveur node.js, et implémentant 
> la spécification ci-dessus et passant le test ci-après

Time line       | Dates          | Remarque
----------------|----------------|----------------
Reception date  | `2016-08-10`   | foo
Deadline        | `2016-08-20`   | bar
Last commit     | `...`          | foo bar

### Prerequisites

- jdk: 1.7 
- scala: 2.11
- sbt: 0.13.8 

### Installation
 
```{bash}
$ git clone https://github.com/DivLoic/Xbmowitnow.git
$ cd Xbmowitnow/
$ sbt compile 
```

### Usage
The main class will read a text file from **src/main/resources/**. 
You need to edit your garden file before run the programme.
```{bash}
$ cd Xbmowitnow/
$ vi src/main/resources/<your-text-file>

$ sbt run "<your-text-file>" 
```
By simply hit `sbt run` you will use the default file specified 
in the configuration file *src/main/resources/default.conf*. 

### Test

```{bash}
$ sbt test
```
The following console should appear:

Feel free to look at the log file create by the unit tests, hit: `cat log/test-test-mowitnow.log`

### logging


### See also
