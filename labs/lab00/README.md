# CST 338 Lab 00

This lab serves as an introduction to IntelliJ, the IntelliJ Workflow, and how to turn in files.

## Outcomes
* build familiarity with IntelliJ
	* project structure
	* creating files
* Look at Java Syntax
* Start exploring encapsulation

## What we will do
* Create a class named 'Rectangle'
	* We will keep building on this
* Create a unit test for our 'Rectangle'

## File Structure
<pre>
project-root/
├── src/
│   └── main/
│       ├── java/
│       │   └── Rectangle.java
│       ├── resources/
│       │
│       ├── test/
│       │   └── RectangleTest.java
│       └── resources/
</pre>
* __main/java__ is where our code lives
* This is where the majority of our work will be done
* __test/java__ is where, you guessed it, our tests will live

* the __resources__ folders are for static files
	*  files that will not change: e.g. icons, images, csvs, sql scripts, etc.
	*  Also where application.properties/application.yml files will be kept when working in SpringBoot
*  __EDIT:__I got rid of this too.  ~~I've included files here because I want git to track them~~
	*  Again, more on git later

## Creating Java Classes
1. Right click on src/main/java
1. Select: New > Java Class
1. Name it Rectangle

### Creating a Java class
In rectangle we are going to have two fields for height and width.  For now they will be ints.  It probably makes more sense for them to be floats but this is just for learning.

1. Add two fields:
  ```java
  private int width;
  private int height;
  ```
1. generate setters and getters
2. generate a parameterized constructor
3. create a no parameter constructor
   1. 	use `this()` to call the parameterized constructor
4. Create a method called `getArea()`
   1. it is public
   2. it takes no parameters
   3. it returns an int (how do we calculate the area of a rectangle? ;) )
5. create a method called `isSquare()`
   1. it is public
   2. it returns a boolean
   3. what seperates a square from a rectangle?

## Creating a Unit Test

__In Rectangle.java__ 
1. right click and select Generate...
2. then select Test...
3. the Testing Library should be `JUnit5`

We will test instantiation, getters, setters, isSquare, and getArea()