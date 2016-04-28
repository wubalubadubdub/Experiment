/**
 * Created by bearg on 4/26/2016.
 */
public class Author implements Comparable<Author> {

    // Note that String also implements Comparable
    private String firstName;
    private String lastName;

    public Author(String first, String last) {
        firstName = first;
        lastName = last;
    }

    @Override
    public int compareTo(Author other) {
        // should return -1 if this is supposed to be less than other,
        // 1 if it's supposed to be greater, or 0 if they're supposed
        // to be equal

        // we'll order them by last name, then by first name if last names
        // are the same
        // String compareTo returns
        int last = this.lastName.compareTo(other.lastName);
        // if the last names are the same, return the value of the comparison
        // for the first names. otherwise, return the value for the last name.
        return last == 0 ? this.firstName.compareTo(other.firstName) : last;
    }

    @Override
    public String toString() {
        return "Author{" +
                lastName +
                ", " +
                firstName +
                "}";
    }


}
