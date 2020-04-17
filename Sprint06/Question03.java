/*Create classes Square and Rectang with method (double getPerimeter()) for calculating of perimeter.
Find solution for avoiding of duplicate code.
Create a double sumPerimeter(List<?> firures) method of the MyUtils class to return a sum perimeters of all figures.
For example, for a given list
[[Square [width=4.00], Square [width=5.00], Rectang [height=2.00, width=3.00]]
you should get 46
*/
import java.util.Arrays;
import java.util.List;

interface Shape {
    double getPerimeter();
}

class Rectang implements Shape {
    private final double width;
    private final double height;

    public Rectang(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }
}

class Square implements Shape {
    private final double size;

    public Square(double size) {
        this.size = size;
    }

    public double getPerimeter() {
        return 4 * size;
    }
}

/*
// Alternative variant (immutability makes it safe):
class Square extends Rectang {
    public Square(double size) {
        super(size, size);
    }
}
*/

public class MyUtils {
    public double sumPerimeter(List<?> figures) {
        return figures.stream()
                .filter(Shape.class::isInstance)
                .map(Shape.class::cast)
                .mapToDouble(Shape::getPerimeter)
                .sum();
    }

     public static void main(String[] args) {
        // smoke test
         List<Object> figures = Arrays.asList(
                 new Square(4),
                 new Square(5),
                 new Rectang(2, 3),
                 new Object(),
                 null
         );
         System.out.println(new MyUtils().sumPerimeter(figures));
     }
}
