package com.idexcel.adminservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LenderCreateDTO {
    private String name;
    private AddressDTO address;
    private PrimaryContactDTO primaryContact;

    public LenderCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public PrimaryContactDTO getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(PrimaryContactDTO primaryContact) {
        this.primaryContact = primaryContact;
    }
}
