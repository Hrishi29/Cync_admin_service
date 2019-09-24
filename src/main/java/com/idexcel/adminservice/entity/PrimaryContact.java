package com.idexcel.adminservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter @ToString
public class PrimaryContact {
    @Id
    private String email;

    private String name;
    //@Column(unique = true)
    private String phone;

    public PrimaryContact() {
    }

    public PrimaryContact(String email) {
        this.email = email;
    }

}
