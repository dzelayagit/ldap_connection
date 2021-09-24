/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valley.ldap.enums;

/**
 *
 * @author dzelaya
 */
public enum KeyNames {

    NAME("name", 1),
    MAIL("mail", 2),
    CN("cn", 3),
    DESCRIPTION("description", 4),
    SAM_ACCOUNT_NAME("sAMAccountName", 5),
    DEPARTMENT("department", 6),
    CO("co", 7),
    C("c", 8),
    L("l", 9),
    COMPANY("company", 10),
    USER_ACCOUNT_CONTROL("userAccountControl", 11),
    TELEPHONE_NUMBER("telephoneNumber", 12),
    SN("sn", 13),
    ST("st", 14),
    GIVEN_NAME("givenName", 15),
    IP_ADDRESS("ipAddress", 16),
    DEVICE_NAME("deviceName", 17),
    SESSION_DURATION("sessionDuration", 19),
    PROFILE("profile", 20),
    STATUS_SERVER("statusServer", 21);

    private KeyNames(String key, int position) {
        this.key = key;
        this.position = position;
    }

    private String key;
    private int position;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
