package com.mahproject.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class ProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @Column(name = "first_name")
    private String firstName = "";

    @Column(name = "lastname")
    private String lastname = "";

    @Column(name = "email")
    private String email = "";

    public ProfileModel(UserModel user, String firstName, String lastname, String email) {
        this.user = user;
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
    }

    public ProfileModel() { }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserModel getUser() {
        return user;
    }
}
