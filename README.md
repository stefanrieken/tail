# Exception based tail recusion

This is a demo of the fact that Java does not support tail recursion,
followed by a demo of how to implement it, in Java, using exceptions.

While it is not to be taken seriously as a usable concept in Java,
it does actually demonstrate a potential mechanism for implementing
tail recursion in other languages using a similar trap invocation.

## Running

This demo is best run interactively from a suitable IDE, but it also
builds (mvn clean install) to run (java -jar target/tail-*.jar).

Enable DO_THROW = true on Tail.java:26 and compile and run again to
see exception based tail recursion in action.

