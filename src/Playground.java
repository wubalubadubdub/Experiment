import java.util.*;
import java.util.function.Predicate;

/**
 * Created by bearg on 4/27/2016.
 */
public class Playground {

    private SortedSet<String> testSet;
    private static int i; // fields (class level variables) are assigned default values.
    // for an int, the default value is 0, so the below method will print the statement.
    private String userName;



    public void printIt() {

        if (i == 0) {
            System.out.println("Value of i is 0");

        }

        int j;
        // if (j == 0) { } -- won't compile. variables that aren't fields must be initialized,
        // and have no default value.
    }


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
     *
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

        Playground p = new Playground();
        p.printIt();



        /**
         * Single abstract method in functional interface ITrade is called check. It takes a Trade object
         * and returns a boolean. Below we use a lambda to implement the method. This lambda just checks
         * to see if a trade is new.
         */

        ITrade newTradeCheck = t -> t.getStatus().equals("NEW"); // use equals method on String

        // same implementation, without using a lambda
        ITrade noLambdaNewTradeCheck = new ITrade() {
            @Override
            public boolean check(final Trade t) {
                return t.getStatus().equals("NEW");
            }
        };

        // a different lambda that checks if the quantity of a trade is "large", i.e. above some value N
        ITrade largeTradeCheck = t -> t.getQuantity() > 1000000;

        List<Trade> tradeList = new ArrayList<>(Arrays.asList(
                new Trade("NEW", 100000000, "GOOG"),
                new Trade("NEW", 100, "GOOG"), // filtered out -- quantity too low
                new Trade("OLD", 100000000, "GOOG") // filtered out -- not a new trade
        ));

        try {
            List<Trade> filteredTradeList = filterTrades(newTradeCheck, tradeList); // filter trades if they're new
            filteredTradeList = filterTrades(largeTradeCheck, filteredTradeList); // filter again for large trades
            filteredTradeList.forEach(t -> System.out.println(t)); // print out filtered trades

        } catch (NullPointerException npe) {
            System.out.println("getStatus() returned null. Can't call equals on null reference.");
        }

        // Use Predicate as a stand in for any function takes a single argument and returns a boolean
        // syntactic sugar -- not required, but makes for cleaner code.
        // Functional interface -- you can implement the method in the same line you declare that you're using one
        Predicate<String> emptyStringChecker = str -> str.isEmpty();
        // same as above line, but using a method reference instead
        // Predicate<String> emptyStringChecker = String::isEmpty;
        String testStr = "test";
        String emptyTestStr = "";
        System.out.println(emptyStringChecker.test(testStr)); // prints false
        System.out.println(emptyStringChecker.test(emptyTestStr)); // prints true



    }

    // the 1st param of this method takes an implementation of the functional interface ITrade, which
    // we will pass in as a lambda. it will use lambda's implementation of ITrade's single method, check,
    // to determine whether to add each trade in the List of trades we pass in to the list of new trades
    // to return.
    public static List<Trade> filterTrades(ITrade tradeLambda, List<Trade> trades) {
        List<Trade> newTrades = new ArrayList<>();
        for (Trade trade : trades) {
            if (tradeLambda.check(trade)) { // this method has no fixed implementation!
                // we are in fact calling a method that can do any number of different things, as long as
                // it takes a Trade object and returns a boolean.
                newTrades.add(trade);
            }
        }

        return newTrades;
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
