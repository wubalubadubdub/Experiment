import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.function.*;

/**
 * Created by bearg on 4/27/2016.
 */
public class Playground {

    private SortedSet<String> testSet;
    private static int i; // fields (class level variables) are assigned default values.
    // for an int, the default value is 0, so the below method will print the statement.
    private String userName;

    // below functions are fields of this class
    public static Function<Double, Double> celsiusToFahrenheit = x -> x * 9 / 5 + 32;
    public static Function<Double, Double> fahrenheitToCelsius = x -> (5.0 / 9.0) * (x - 32);

    private static double convertTemp(Function<Double,Double> convertTo, double beforeTemp) {
        double afterTemp = convertTo.apply(beforeTemp);
        return afterTemp;
    }

    private static double convertToF(double startTemp) { // takes a double and returns a double.
        // we can use it an implementation of our Converter interface using a method reference.
        return startTemp * 9 / 5 + 32;
    }

    // figure out how to correctly implement later
    private static void printConvertedTemp(Function<Double,Double> convertTo, double beforeTemp) {

        if (true) {
            System.out.println(convertTemp(convertTo, beforeTemp) + " F");

        } else if (false) {
            System.out.println(convertTemp(convertTo, beforeTemp) + " C");

        } else {
            System.out.println("Didn't work");
        }
    }



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

        Vehicle vehicle = new Vehicle();
        vehicle.drive();

        Vehicle carVehicle = new Car();
        carVehicle.drive();

        Vehicle truckVehicle = new Truck();
        truckVehicle.drive();

        // Won't compile -- Vehicle has no load() method, even though Truck does
        // the reference type (Vehicle) is used to determine which methods can be called,
        // not the Object type (Truck)
        // truckVehicle.load()

        // truckVehicle = new Vehicle(); // we lied to the compiler!
        ((Truck)truckVehicle).load(); // it'll compile if we promise compiler it's a Truck at runtime
        // uncomment above line to lie to the compiler, which will throw a ClassCastException
        // since truckVehicle is not a Truck at runtime.

        // alternatively, we can just declare the reference type as Truck also
        Truck truck = new Truck();
        truck.load();







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

        double temp = 72.0d;
        printConvertedTemp(fahrenheitToCelsius, temp);
        try {
            Class cls = p.getClass();
            Field field = cls.getField("fahrenheitToCelsius");
            System.out.println("Worked!");

        } catch(Exception e) {
            System.out.println("Exception!");
        }

        Converter<String, Integer> converter = f -> Integer.valueOf(f);
        System.out.println(converter.convert("5"));

        Converter<Double, Double> fahToCelsius = f -> {
            return (5.0 / 9.0) * (f - 32);
        };

        Converter<Double, Double> celToFahrenheit = c -> (c * 9.0) / 5.0 + 32; // creates anon inner class
        // that implements Converter's convert method by doing the calculation shown. the convert method will
        // take a Double and return a Double


        double startTemp = 90.025d;
        double convertedTemp = celToFahrenheit.convert(startTemp);
        System.out.printf("%.3f C is %.3f F", startTemp, convertedTemp);
        System.out.println();

        BigDecimal startCurrencyPrice = new BigDecimal("1058.34");
        startCurrencyPrice.setScale(20, BigDecimal.ROUND_HALF_EVEN);

        // converter can be used to convert between any two units
        Converter<BigDecimal, BigDecimal> usdToEuro = usd -> {
            BigDecimal exchangeRateMultiplier = new BigDecimal("0.873285"); // this will vary
            return usd.multiply(exchangeRateMultiplier);
        };


        final BigDecimal endCurrencyPrice = usdToEuro
                .convert(startCurrencyPrice).round(
                new MathContext(3)); // doesn't work quite right. 1058.34 USD should be 924.00 Euros
        // but shows as 924.00 instead.

        System.out.printf("%.2f USD is %.2f Euros", startCurrencyPrice, endCurrencyPrice);

        Consumer<Author>  greeter = author -> {
            System.out.println("\nHello, " + author.getFirstName());
        };

        greeter.accept(new Author("Bob", "Barker"));

        // takes two Integers sums them, returning a third Integer
        BiFunction<Integer, Integer, Integer> sumNumbers = (n1, n2) -> n1 + n2;
        System.out.println("preparing to call sumNumbers.apply");
        System.out.println(sumNumbers.apply(1, 7));

        BinaryOperator<String> concat = (s, s2) -> s + s2;

        List<String> stringCollection = new ArrayList<> ();
        Collections.addAll(stringCollection, "duck", "goose", "horse", "cow", "zebra", "cat");

        stringCollection
                .stream()
                .sorted((str1, str2) -> str2.compareTo(str1)) // takes a comparator. empty means use natural order
                .forEach(System.out::println); // prints them in reverse alphabetical order

        // another way to do the above:
         /*stringCollection
                 .stream()
                 .sorted(Comparator.<String, String>comparing(String::toLowerCase).reversed());*/

        // Comparator.comparing static method takes a function as an argument, which can be given using a long-form
        // lambda expression, as shown below, or a short-form lambda, i.e.
        // method reference, as above

        System.out.println("Beginning print by length");
        stringCollection
                .stream()
                .sorted(Comparator.comparing(s -> s.length())) // function takes String s and returns int s.length.
                // shorter strings are printed before longer strings
                .forEach(System.out::println);

        // below, the hard way
        /*stringCollection
                .stream()
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(final String s1, final String s2) {
                        int lenStr1 = s1.length();
                        int lenStr2 = s2.length();
                        if (lenStr1 < lenStr2) {
                            return -1; // 1st string is longer
                        } else if (lenStr1 == lenStr2){
                            return 0; // same length
                        } else {
                            return 1; // 2nd string is longer
                        }
                    }
                })
                .forEach(System.out::println);
*/

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

    // can use a method reference to refer to existing method for interface implementation
    Converter<Double, Double> toFConverter = Playground::convertToF;

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
