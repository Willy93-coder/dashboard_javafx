package Dashboard.controllers;

public class User {
    private String email;
    private String username;
    private String password;
    private String confirm;
    private String LastName1;
    private String LastName2;

    public User(String email, String username, String password, String confirm, String lastName1, String lastName2) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirm = confirm;
        LastName1 = lastName1;
        LastName2 = lastName2;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm() {
        return confirm;
    }

    public String getLastName1() {
        return LastName1;
    }

    public String getLastName2() {
        return LastName2;
    }


}
