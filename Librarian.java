package com.example.library.models;

public class Librarian {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;

    public Librarian() {}
    public Librarian(int id, String firstname, String lastname, String email, String username) {
        this.id = id; this.firstname = firstname; this.lastname = lastname; this.email = email; this.username = username;
    }
    public int getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public void setId(int id) { this.id = id; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setEmail(String email) { this.email = email; }
    public void setUsername(String username) { this.username = username; }
}
