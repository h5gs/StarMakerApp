package com.archirayan.starmakerapp.model;

/**
 * Created by archirayan on 1/3/18.
 */

public class ContactListItem {

    private String ContactImage;
    private String ContactName;
    private String ContactNumber;

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
}
