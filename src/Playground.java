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

}
