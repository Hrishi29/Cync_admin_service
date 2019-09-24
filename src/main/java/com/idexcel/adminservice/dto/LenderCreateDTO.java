package com.idexcel.adminservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LenderCreateDTO {
    private String name;
    private AddressDTO address;
    private PrimaryContactDTO primaryContact;
}
