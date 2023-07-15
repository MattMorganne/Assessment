package com.eviro.assessment.grad001.MorganneMatoka.Assessment.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AccountProfile {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private String httpImageLink;

    public AccountProfile() {
    }

    public AccountProfile( String name, String surname, String httpImageLink) {

        this.name = name;
        this.surname = surname;
        this.httpImageLink = httpImageLink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getHttpImageLink() {
        return httpImageLink;
    }

    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }

    @Override
    public String toString() {
        return "AccountProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", httpImageLink='" + httpImageLink + '\'' +
                '}';
    }
}
