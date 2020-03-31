// Q1
public class HelloWorld {
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
}

// Q2
class Q2 {
  public static float doubleNumber(double number) {
       return (float) (2.0 * number);
  }
}

// Q3
class Q3 {
  public static int century(int year) {
      return (1 + (year - 1) / 100);
  }
}

// Q4
class Q4 {
  public static int sumOfDigits(int number) {
    int res = 0;
    while (number > 0) {
      res += number % 10;
      number /= 10;
    }
    return res;
  }
}

// Q5
class Q5 {
  public static boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
  }
}
