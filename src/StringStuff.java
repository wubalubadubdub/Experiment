/**
 * Created by bearg on 4/24/2016.
 */
public class StringStuff {

    public static void reverser(String str) {
        String reversedStr = ""; // must be initialized before it's used in for loop
        for(int i = 0; i < str.length(); i++) {
            reversedStr = reversedStr + str.charAt((str.length()-1)- i);
        }
        System.out.println(reversedStr);
    }

    public static String cloudToButt(String str) {
        if(!str.contains("cloud")) {
            return "The word cloud does not appear in this string!";
        }

        else{
            str = str.replaceAll("cloud", "butt");
            return str;
        }
    }



}
