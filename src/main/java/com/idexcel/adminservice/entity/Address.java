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



}
