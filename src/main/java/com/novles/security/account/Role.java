package com.novles.security.account;

public enum Role {
    SUPER_ADMIN, ADMIN, MEMBER;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
