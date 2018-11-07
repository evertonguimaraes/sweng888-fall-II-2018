package movies.sweng888.psu.edu.moviesapp.model.entity.entity;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;

public class UserProfile implements Serializable {

    private String name;
    private String surname;
    private String username;
    private String phone; // allows null
    private String email;
    private String password;
    private String birthday; // allows null

    public UserProfile() {
    }

    public UserProfile(String name, String surname, String username, @Nullable String phone, String email, String password, @Nullable String birthday ) {
            this.name = name;
        this.surname = surname;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public UserProfile(String name, String surname, String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
