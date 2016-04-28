/**
 * Created by bearg on 4/27/2016.
 * Singleton for a Duck.
 */
public class Duck {

    private static Duck duckInstance;

    private Duck() {

    }

    public static Duck getDuckInstance() {
        if (duckInstance == null) {
            duckInstance = new Duck();
            return duckInstance;
        }else {
            return duckInstance;
        }

    }

}
