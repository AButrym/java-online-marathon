/*
 * Suppose we have class hierarchy that corresponds next class diagram:

    Shape
   |     |
Square  Circle

 * Using Wrapper generic class from task #1, modify it so that it can only wrap objects that implement
 * the Shape interface (Square, Circle, etc.).
 */

class Wrapper<T extends Shape> {
    private T value;
    public Wrapper(T value) { this.value = value; }
    public void setValue(T value) { this.value = value; }
    public T getValue() { return value; }
}
