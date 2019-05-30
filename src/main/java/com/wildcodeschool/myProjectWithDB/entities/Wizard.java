package com.wildcodeschool.myProjectWithDB.entities;

import java.sql.Date;

public class Wizard {

    private int id;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String birthPlace;
    private String biography;
    private boolean isMuggle;

    public Wizard(int id, String firstname, String lastname, Date birthday, String birthPlace, String biography, boolean isMuggle) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.birthPlace = birthPlace;
        this.biography = biography;
        this.isMuggle = isMuggle;
    }


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getBiography() {
        return biography;
    }

    public boolean isMuggle() {
        return isMuggle;
    }


}
