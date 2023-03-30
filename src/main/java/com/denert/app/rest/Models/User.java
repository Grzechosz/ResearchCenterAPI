package com.denert.app.rest.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String surname;

    @Column(length = 9)
    private int phoneNumber;

    @Column(length = 45)
    private String password;

    @Column(length = 45)
    private String mail;

    @Column(length = 1)
    private int permissions;

    @OneToMany(mappedBy = "user")
    private Set<Agreement> projectsAgreements;

    @OneToMany(mappedBy = "user")
    private Set<Agreement> projectsCommission;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "addressId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Address address;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public Set<Agreement> getProjectsAgreements() {
        return projectsAgreements;
    }

    public void setProjectsAgreements(Set<Agreement> projectsAgreements) {
        this.projectsAgreements = projectsAgreements;
    }

    public Set<Agreement> getProjectsCommission() {
        return projectsCommission;
    }

    public void setProjectsCommission(Set<Agreement> projectsCommission) {
        this.projectsCommission = projectsCommission;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
