package com.ms.ex.booksearch.booksearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class AbstractTimestamp {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = true, updatable = false)
    private Date registerDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = true)
    private Date updateDatetime;

    @PrePersist
    protected void onCreate() {
        updateDatetime = registerDatetime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDatetime = new Date();
    }

    @JsonIgnore
    public Date getUpdateDate() {
        return updateDatetime;
    }

    @JsonIgnore
    public Date getCreateDate() {
        return registerDatetime;
    }
}
