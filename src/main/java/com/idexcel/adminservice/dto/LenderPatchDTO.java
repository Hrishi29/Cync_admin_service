package com.idexcel.adminservice.dto;

import com.idexcel.adminservice.enums.LenderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LenderPatchDTO {
    private String id;
    private LenderStatus status;
}
