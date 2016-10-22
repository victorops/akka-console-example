# VictorOps Akka Console Example

This is a thing we use here at VictorOps, and it's awesome.

## Run the example

```
$ git clone https://github.com/victorops/akka-console-example.git 
```

In terminal one:

```
$ cd akka-console-example
$ sbt counter/run
```

In terminal two:

```
$ cd akka-console-example
$ sbt console/run
```

In the ammonite REPL:

```
@ getCount
@ incrementCount
@ resetCount
```
