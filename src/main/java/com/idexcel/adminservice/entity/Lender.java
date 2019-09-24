package com.idexcel.adminservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter @ToString
public class Lender {
    @Id
    private String id;
    private String name;

    @OneToOne(mappedBy = "lender", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne
    private PrimaryContact primaryContact;

    private  String status;
    private Date createdBy;
    private Date createdDate;
    private Date updatedBy;
    private Date updatedDate;

    public Lender() {
        this.id = UUID.randomUUID().toString();
    }


}
