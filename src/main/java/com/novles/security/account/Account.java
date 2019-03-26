package com.novles.security.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@Table(name = "SKUSER", schema = "DEV")
@Alias("accountUser")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seqno;

    private String id;
    private String name;

    @JsonIgnore
    private String password;

    @OneToMany
    @JoinColumn(name = "USER_SEQNO")
    private List<AccountRole> roles;
}
