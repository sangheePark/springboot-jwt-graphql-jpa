package com.novles.security.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "USER_ROLE")
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long seqno;

    @JsonIgnore
    @Column(name = "USER_SEQNO", insertable = false, updatable = false)
    private Long userSeqno;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME", insertable = false, updatable = false)
    protected Role role;
}
