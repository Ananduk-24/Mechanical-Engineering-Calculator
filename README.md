# Mechanical Engineering Calculator (Java)

A simple terminal program for mechanical engineering calculations, written in Java.
All inputs use SI units.

## Features

1. Beam bending stress (simply supported beam, central point load, rectangular section)
2. Gear ratio and output speed
3. Shaft power and torque conversion
4. Heat conduction through a flat wall (Fourier's law)
5. Factor of safety

## Formulas used

| Calculation | Formula |
|---|---|
| Max bending moment | M = P x L / 4 |
| Moment of inertia (rectangle) | I = b x h^3 / 12 |
| Bending stress | sigma = M x (h/2) / I |
| Gear ratio | driven teeth / driver teeth |
| Power | P = 2 x pi x N x T / 60 |
| Heat conduction | Q = k x A x (T1 - T2) / L |
| Factor of safety | strength / working stress |

## How to run

1. Install the JDK (version 11 or newer) from https://adoptium.net
2. Check it works: `java -version` and `javac -version`
3. Open a terminal in this folder and run:

```
javac MechCalculator.java
java MechCalculator
```

Choose a number from the menu and enter the values it asks for. Enter 0 to exit.

## Example

Beam with P = 1000 N, L = 2 m, b = 0.05 m, h = 0.1 m gives a maximum bending stress of 6.00 MPa.

## Ideas to extend it

- Add more beam types (cantilever, uniformly distributed load)
- Add a column buckling (Euler) calculation
- Save results to a text file
- Build a window interface with Java Swing
- Upload the project to GitHub for your portfolio
