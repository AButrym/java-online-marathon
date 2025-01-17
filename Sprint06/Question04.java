/* Please create class Shape with abstract method to calculate area of figure and field name.
 * Replace code in method getArea() according to principles of polymorphism i.e.
 * Circle and Rectangle classes extends Shape class and override double getArea() method.
 * Develop maxAreas() method of the MyUtils class to return a List with instances of maximum area.
 * The original list must be unchanged.
 * For example, for a given list
 * [Circle [radius=2.00], Rectangle [height=2.00, width=3.00],
 *  Circle [radius=1.00], Rectangle [height=3.00, width=2.00],
 *  Circle [radius=0.50], Rectangle [height=1.00, width=2.00]]
 * you should get
 * [Circle [radius=2.00], Rectangle [height=2.00, width=3.00],
 *  Rectangle [height=3.00, width=2.00]]
 * */

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

abstract class Shape {
    private final String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(name, shape.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "name='" + name + '\'' +
                '}';
    }

    public abstract double getArea();
}

class Circle extends Shape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public Circle(double radius) {
        this("Circle", radius);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double height;
    private double width;

    public Rectangle(String name, double height, double width) {
        super(name);
        this.height = height;
        this.width = width;
    }

    public Rectangle(double height, double width) {
        this("Rectangle", height, width);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.height, height) == 0 &&
                Double.compare(rectangle.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height, width);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}

class MyUtils {
    public List<Shape> maxAreas(List<Shape> shapes) {
        double maxCircleArea = shapes.stream()
                .filter(Circle.class::isInstance)
                .mapToDouble(Shape::getArea)
                .max().orElse(0);
        double maxRectangleArea = shapes.stream()
                .filter(Rectangle.class::isInstance)
                .mapToDouble(Shape::getArea)
                .max().orElse(0);
        Predicate<Shape> filterMax = shape ->
                shape instanceof Circle && shape.getArea() == maxCircleArea ||
                shape instanceof Rectangle && shape.getArea() == maxRectangleArea;

        return shapes.stream()
                .filter(filterMax)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // smoke test
        List<Shape> shapeList = Arrays.asList(
                new Circle(2.00),
                new Rectangle(2.00, 3.00),
                new Circle(1.00),
                new Rectangle(3.00, 2.00),
                new Circle(0.50),
                new Rectangle(1.00, 2.00),
                null
        );
        new MyUtils().maxAreas(shapeList).forEach(System.out::println);
    }
}
