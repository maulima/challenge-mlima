
# Ten-PinBowling Jobsity Challenge 
 
Demo repository for Jobsity code challenge

## Framework

* [Gradle](https://gradle.org/) - Build mechanism 
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Development Kit 

## Instructions 

Please follow these instruction to use this application

- Download (Zip file) or clone the project from repository.
- Unzip file(ONLY if the file was downloaded)
- Open command line application (Depend on the operation system)
- Change the path where the project was unzipped or cloned, then type the following command:
```shell
gradle build
```
- to execute main task, type the following command
```shell
./gradlew runMain
```

## File Input

There are four test cases, located at resources folder, each file has the following description:

- input-all0.txt, when a player scored 0 on all of the plays.
- input-allF.txt, when a player fails on all of the plays.
- input-allS.txt, when a player scored 10 on all of the plays. Perfect game.
- input-test.txt, a game whit two players. Regular Game
