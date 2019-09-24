package com.idexcel.adminservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AddressDTO {

    private String id;
    private String street;
    private String city;
    private String state;
    private String country;
    private int zipcode;
}
