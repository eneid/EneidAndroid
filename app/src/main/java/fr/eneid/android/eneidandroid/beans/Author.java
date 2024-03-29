package fr.eneid.android.eneidandroid.beans;

public class Author {

    private String email;
    private String firstName;
    private String name;

    public Author() {
    }

    public Author(String email, String firstName, String name) {
        this.email = email;
        this.firstName = firstName;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
