# MowItNow
###### Xebia technical test - Loïc DIVAD (@DivLoic)

This project is an answer to the technical test MowitNow.
> La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires.        
> [...]       
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

The main class will read a text file from **src/main/resources/**. You need to edit your garden file 
before run the program.
```{bash}
$ cd Xbmowitnow/
$ vi src/main/resources/<your-text-file>

$ sbt "run <your-text-file>" 
```
By simply hit `sbt run` you will use the default file specified in the configuration file 
*src/main/resources/default.conf*. 

### logging

After a **sbt run** the program will only out put the result. Hit `cat logs/out.log` to see the every steps.
You can also change the logger to a console appender with a configuration file as follows:
```{bash}
$ vi src/main/resources/default.conf
```
```{text}
dev {
  logger = console #root
  ...
```

### Test

```{bash}
$ sbt test
```
The following console should appear:
![test stack](https://github.com/DivLoic/Xbmowitnow/raw/master/src/main/resources/img/sbt_test.png)
Feel free to look at the log file create by the unit tests, hit: `cat logs/test.log`


### Exception :collision:
*Note that we introduce a* `WrongToolUsageException` *for the developers that may use
the Tool interface from MowitNow.*

### See also
