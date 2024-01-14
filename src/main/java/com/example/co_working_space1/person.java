package com.example.co_working_space1;

import java.io.Serializable;

public abstract class Person implements Serializable {
    protected String username;
    protected String password;

    Person()
    {

    }
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract int login(String username, String password);


}
