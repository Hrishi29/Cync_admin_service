package com.idexcel.adminservice.dto;

import com.idexcel.adminservice.enums.LenderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LenderUpdateDTO {
    private String id;
    private String name;
    private AddressDTO address;
    private PrimaryContactDTO primaryContact;
    private LenderStatus status;

    public LenderUpdateDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LenderStatus getStatus() {
        return status;
    }

    public void setStatus(LenderStatus status) {
        this.status = status;
    }
}
