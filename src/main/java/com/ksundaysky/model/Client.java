package com.ksundaysky.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private int id;

    @Column(name = "client_name")
    @Size(max=20, message = "Zbyt długie imię - max 20 znaków")
    @Pattern(regexp="[A-Za-z ]*", message="Możesz używać tylko liter i spacji")
    @NotEmpty(message = "*Uzupełnij imię klienta")
    private String client_name;

    @Column(name = "client_surname")
    @Size(max=30,message = "Zbyt długie nazwisko - max 30 znaków")
    @NotEmpty(message = "*Uzupełnij nazwisko klienta")
    @Pattern(regexp="[a-zA-Z' ]*", message = "*Niepoprawny format nazwiska")
    private String client_surname;

    @Column(name = "phone_number")
    @Digits(integer=10, fraction=0, message="*Numer telefonu powinien zawierać tylko cyfry")
    @NotEmpty(message = "*Uzupełnij numer telefonu")
    private String phone_number;

    @Column(name = "city")
    @Pattern(regexp="[a-zA-Z- ]*", message = "*Niepoprawny format miasta")
    @Size(max=30, message = "*Zbyt długa nazwa miasta")
    private String city;

    @Column(name = "postcode")
    @Pattern(regexp="(\\d{2}-\\d{3}){0,1}", message = "*Przykładowy kod pocztowy: 12-345")
    private String postcode;

    @Column(name = "street")
    @Pattern(regexp="[a-zA-Z-' ]*", message = "*Niepoprawny format ulicy")
    @Size(max=30, message = "Zbyt długa nazwa ulicy")
    private String street;

    @Column(name = "street_local_number")
    @Size(max=10, message = "Zbyt długi numer domu i lokalu")
    @Pattern(regexp="[a-zA-Z0-9 ]*",message = "*Niepoprawny numer domu")
    private String street_local_number;

    public Client() {
    }

    public int getId() {

        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }
    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_surname() {
        return client_surname;
    }

    public void setClient_surname(String client_surname) {
        this.client_surname = client_surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_local_number() {
        return street_local_number;
    }
    public void setStreet_local_number(String street_local_number) {
        this.street_local_number = street_local_number;
    }

}
