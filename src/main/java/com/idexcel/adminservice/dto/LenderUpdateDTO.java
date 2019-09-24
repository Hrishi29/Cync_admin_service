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
}
