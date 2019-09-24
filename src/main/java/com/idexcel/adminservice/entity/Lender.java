package com.idexcel.adminservice.entity;

import com.idexcel.adminservice.enums.LenderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
@Getter @Setter @ToString
public class Lender {
    @Id
    private String id;
   // @Column(unique = true)
    private String name;

  //  @OneToOne(mappedBy = "lender", cascade = CascadeType.ALL)
    private Address address;

 //   @OneToOne
    private PrimaryContact primaryContact;

    private LenderStatus status;
    private Date createdBy;
    private Date createdDate;
    private Date updatedBy;
    private Date updatedDate;

    public Lender() {
        this.id = UUID.randomUUID().toString();
        this.status = LenderStatus.ACTIVE;
    }


}
