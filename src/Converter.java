/**
 * Created by bearg on 4/30/2016.
 * Converter takes in a type F, does something with it, and returns type T
 */
@FunctionalInterface
public interface Converter<F, T> {

    T convert(F from);
}
