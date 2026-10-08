# Linear-Programming-Project-Java

Java learning exercise for transforming linear-programming coefficient arrays into a proposed standard form.

## Repository guide

### Contents

- [LinearProgramming.java](LinearProgramming.java)
- [README.md](README.md)

### Getting started

```bash
git clone https://github.com/Raimal-Raja/Linear-Programming-Project-Java.git
cd Linear-Programming-Project-Java
```

Use a JDK and compile individual exercises separately; repeated class names may appear across folders. Example:

```bash
javac "LinearProgramming.java"
```

Java compilation was checked with the Eclipse compiler and Java 21 runtime. With a local JDK, run `javac LinearProgramming.java LinearProgrammingTest.java` and `java LinearProgrammingTest`.

### Configuration and limitations

The converter accepts explicit <=, >=, and = relations, adding a slack or surplus variable for each inequality. The original three-argument constructor assumes <= constraints. It converts equations and does not optimize an objective or implement simplex feasibility phases.

### Validation

Reviewed on 2026-10-08. Repository structure and documentation were reviewed. The converter compiled successfully, and its regression program passed checks for slack signs, equality constraints, dimensions, and the original coefficient-sum bug.

### Contributions

Describe the issue, reproduction steps, environment, and expected behavior when proposing a change. Keep generated environments, credentials, and unnecessary build artifacts out of new commits.

### License

No top-level license file was found during this review.
