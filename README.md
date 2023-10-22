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
# Simulations completed: 2000000 simulations run in 2991ms, 665995 cars won (33.29975%)
```

- Run 2 millions games, choose a random door and always switch
```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -g2000000 -ss=a
# Simulations completed: 2000000 simulations run in 3334ms, 1332348 cars won (66.6174%)
```

- Run 100k games with 2 threads, only choose first door (door #0), never switch

```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -t os -n 2 -g100000 -pds=0 -ss=n
# Simulations completed: 100000 simulations run in 614ms, 33393 cars won (33.393%)
```

- Run 100k games, car is always behind the third door (door #2), player only choose middle door (#1), never switch
```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -g100000 -cds=2 -pds=1 -ss=n
# Simulations completed: 100000 simulations run in 578ms, 0 cars won (0.0%)
```
