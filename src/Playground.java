import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by bearg on 4/27/2016.
 */
public class Playground {

    private SortedSet<String> testSet;

    // initialization block called for each instance of Playground class
    {
        testSet = new TreeSet<>();
        testSet.add("some string");
        testSet.add("another string");

    }

    public SortedSet<String> getSet() { // give String type parameter, otherwise it's a set of objects
        return testSet;
    }

    /**
     * We must either return a value or throw an Exception with a
     * return type of int.
     * @param a first number
     * @param b second number
     * @return the value of first number / second number, as an int
     */
    public static int divide(int a, int b) throws DivisionByZeroException { // note: floating point division by 0 is allowed and is infinity
        if (b == 0) {
            throw new DivisionByZeroException("You can't divide by 0"); // unchecked exception since inherits
            // from RuntimeException
        }

        return (a / b);

    }

    private static Dog aDog = new Dog("Rover");
    private static Dog anotherDog = new Dog("Fido");

    public static void main(String[] args) {

        changeReference(anotherDog);
        System.out.println(aDog == anotherDog); // true. they both reference the Dog with name "Fido"
        System.out.println(aDog.getName()); // Fido

        wontChangeReference(aDog);
        System.out.println(aDog.getName()); // still Fido, not Max
    }

    public static void changeReference(Dog anotherDog) { // really just a setter method for aDog
        aDog = anotherDog;
    }

    // when the below method returns, anotherReferenceToPassedInDog ceases to exist. this method does
    // nothing but create a new Dog which is destroyed when it returns. it DOES NOT affect the reference
    // passed in in any way, because its value is merely COPIED to anotherReferenceToPassedInDog.
    public static void wontChangeReference(Dog anotherReferenceToPassedInDog) {
        Dog tempDog = new Dog("Max");
        anotherReferenceToPassedInDog = tempDog; // reassignment here does NOT affect the reference passed in
    }

}
