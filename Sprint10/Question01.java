/*
Create a Wrapper generic class with generic private field of type T named value.
Also in the Wrapper class define a parametrized public constructor and access methods with setValue and getValue name.
*/

class Wrapper<T> {
    private T value;
    public Wrapper(T value) { this.value = value; }
    public void setValue(T value) { this.value = value; }
    public T getValue() { return value; }
}
