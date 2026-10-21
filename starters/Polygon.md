# Polygon starter

Starter code for the `Polygon` work in **Lab02**. It gives two static helpers that map
between a side count and a polygon's name, so the naming is not the part of the lab anyone
spends time on.

**This is not the whole class.** Lab02 asks for a `Polygon` that implements `Shape`, with
`sideCount` and `sideLength` fields and a `getArea()` that returns

```
(sideCount * sideLength * sideLength) / (4 * Math.tan(Math.PI / sideCount))
```

Add that to what is below; the two helpers here are a starting point, not a solution.

The same code as a plain file: [`Polygon.java`](Polygon.java).

```java
/**
 * A representation of a regular polygon.
 * a regular polygon is one where all the sides are the same length
 *
 * @author Drew "Dr.C" Clinkenbeard
 * @version 0.1.0
 * @since 7/2/25
 */
public class Polygon {

  public static String getPolygonName(int sides) {

    if (sides < 3) {
      return "Fewer than 3 sides is not a polygon.";
    }

    return switch (sides) {
      case 3 -> "Triangle";
      case 4 -> "Square";
      case 5 -> "Pentagon";
      case 6 -> "Hexagon";
      case 7 -> "Heptagon";
      case 8 -> "Octagon";
      case 9 -> "Nonagon";
      case 10 -> "Decagon";
      case 11 -> "Hendecagon";
      case 12 -> "Dodecagon";
      case 13 -> "Tridecagon";
      case 14 -> "Tetradecagon";
      case 15 -> "Pentadecagon";
      default -> "I don't know what that is called";
    };
  }

  public static int getNumberOfSides(String name) {
    return switch (name.toLowerCase()) {
      case "triangle" -> 3;
      case "square" -> 4;
      case "pentagon" -> 5;
      case "hexagon" -> 6;
      case "heptagon" -> 7;
      case "octagon" -> 8;
      case "nonagon" -> 9;
      case "decagon" -> 10;
      case "undecagon" -> 11;
      case "dodecagon" -> 12;
      case "tridecagon" -> 13;
      case "tetradecagon" -> 14;
      case "pentadecagon" -> 15;
      default -> -1; 
    };
  }
}
```
