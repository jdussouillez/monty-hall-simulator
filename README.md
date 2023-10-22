# monty-hall-simulator

Run [Monty Hall problem](https://en.wikipedia.org/wiki/Monty_Hall_problem) simulations in parallel, using different strategies.

## Build

```sh
./mvnw package
```

## Usage

Get the help to see the list of parameters:

```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -h
```

### Examples

- Run 2 millions games, choose a random door but never switch
```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -g2000000 -ss=n
# Simulations completed: 2000000 simulations run, 666193 cars won (33.309650000000005%)
```

- Run 2 millions games, choose a random door and always switch
```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -g2000000 -ss=a
# Simulations completed: 2000000 simulations run, 1332480 cars won (66.62400000000001%)
```

- Run 100k games with 2 threads, only choose first door (door #0), never switch

```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -t os -n 2 -g100000 -pds=0 -ss=n
# Simulations completed: 100000 simulations run, 33268 cars won (33.268%)
```

- Run 100k games, car is always behind the third door (door #2), player only choose middle door (#1), never switch
```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -g100000 -cds=2 -pds=1 -ss=n
# Simulations completed: 100000 simulations run, 0 cars won (0.0%)
```
