package com.idexcel.adminservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter @ToString
public class Address {

    @Id
    private String id;
    private String street;
    private String city;
    private String state;
    private String country;
    private int zipcode;

   // @OneToOne
   // @MapsId
    private Lender lender;

    public Address(){}

    public Address(String id, String street, String city, String state, String country, int zipcode, Lender lender) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.lender = lender;
    }


}
