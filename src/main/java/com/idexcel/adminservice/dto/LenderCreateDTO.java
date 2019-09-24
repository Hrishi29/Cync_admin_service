package com.idexcel.adminservice.dto;

import com.idexcel.adminservice.entity.Address;
import com.idexcel.adminservice.entity.PrimaryContact;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LenderCreateDTO {
    private String name;
    private Address address;
    private PrimaryContact primaryContact;
}
