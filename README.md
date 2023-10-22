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

- Run 3 games and see the game results
```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -g3 -ss=n -v
# Doors=[{G}, {C}, {G}] | wonCar=false
# Doors=[{G}, {G}, {C}] | wonCar=true
# Doors=[{G}, {C}, {G}] | wonCar=false
# Simulations completed: 3 simulations run in 11ms, 1 cars won (33.33333333333333%)
```

- Run 1 game and see the game details (car is behind #1, player chooses #0 but switch and win)
```sh
java -jar target/monty-all-simulator-jar-with-dependencies.jar -g1 -cds=1 -pds=0 -ss=a -vv
# Doors initialized with strategy com.github.jdussouillez.montyhallsim.bean.DoorStrategy$Fixed@6956de9, car door is 1
# Player chooses door 0
# Host opens door 2: goat
# Player switches door
# Player chooses door 1
# Host opens door 0: goat
# Host opens door 1: car
# Player wins the car! :-D
# Doors=[{G}, {C}, {G}] | wonCar=true
# Simulations completed: 1 simulations run in 18ms, 1 cars won (100.0%)
```
