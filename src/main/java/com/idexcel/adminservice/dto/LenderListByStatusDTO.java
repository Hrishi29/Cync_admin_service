package com.idexcel.adminservice.dto;

import com.idexcel.adminservice.entity.Address;
import com.idexcel.adminservice.entity.PrimaryContact;
import com.idexcel.adminservice.enums.LenderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class LenderListByStatusDTO {

    private String id;
    private String name;
    private Address address;
    private PrimaryContact primaryContact;
    private LenderStatus status;
    private Date createdBy;
    private Date createdDate;
    private Date updatedBy;
    private Date updatedDate;
}
