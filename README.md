# horus_exercise_solution
Solution for recruitment exercise from Horus

The project uses Maven as the build tool.
The solution contains both the implementation of requested methods in the Wall class, as well as a suite of unit tests. The implementations of Block and CompositeBlock interfaces were only done for the purpose of writing tests and those classes do not contain any logic.

When implementing the requested findBlockByColor, findBlocksByMaterial and count methods, I have assumed that it is acceptable for objects implementing Block and CompositeBlock to have null values, therefore my code simply ignores those values (instead of e.g. throwing an Exception). Additionally, the Wall class was documented by means of annotation with Javadoc comments.
