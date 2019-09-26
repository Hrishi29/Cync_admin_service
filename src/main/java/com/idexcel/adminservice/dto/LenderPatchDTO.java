package com.idexcel.adminservice.dto;

import com.idexcel.adminservice.enums.LenderStatus;

import javax.validation.constraints.NotNull;


public class LenderPatchDTO {
    @NotNull
    private String id;
    @NotNull
    private LenderStatus status;

    public LenderPatchDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LenderStatus getStatus() {
        return status;
    }

    public void setStatus(LenderStatus status) {
        this.status = status;
    }
}
