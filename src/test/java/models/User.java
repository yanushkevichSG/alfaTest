package models;

import utilities.Environment;

public class User {
    private String firstName;
    private String lastName;
    private Credentials credentials;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return credentials.getLogin();
    }

    public String getPassword() {
        return credentials.getPassword();
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public static User defaultUser() {
        return builder()
                .withCredentials(new Credentials(Environment.login(), Environment.password()))
                .build();
    }

    public static User invalidUser() {
        return builder()
                .withCredentials(new Credentials(Environment.invalidLogin(), Environment.invalidPassword()))
                .build();
    }

    public static NewUserBuilder builder() {
        return new User().new NewUserBuilder();
    }

    public class NewUserBuilder {

        public NewUserBuilder withFirstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public NewUserBuilder withLastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public NewUserBuilder withCredentials(Credentials credentials) {
            User.this.credentials = credentials;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
