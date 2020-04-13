/* Create class Animal with fields age (int) and weight (double) that can be accessed in the same package only.
 * Animal should have method move without implementation, accessible from everywhere in the application.
 * Animal should have a public constructor with parameters age and weight for initialization corresponding fields.
 *
 * Create Descendant classes Mammal, Fish, and Bird.
 * For Mammal method move returns "run", for Fish - "swim", for Bird - "fly".
 * Make it impossible to create descendants of Fish and Bird.
 * Forbid overriding method move for Mammal.
 */
abstract class Animal {
    int age;
    double weight;

    public Animal(int age, double weight) {
        this.age = age;
        this.weight = weight;
    }

    public abstract String move();
}

class Mammal extends Animal {
    public Mammal(int age, double weight) {
        super(age, weight);
    }

    @Override
    public final String move() {
        return "run";
    }
}
final class Fish extends Animal {
    public Fish(int age, double weight) {
        super(age, weight);
    }

    @Override
    public String move() {
        return "swim";
    }
}
final class Bird extends Animal {
    public Bird(int age, double weight) {
        super(age, weight);
    }

    @Override
    public String move() {
        return "fly";
    }
}
