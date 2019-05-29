package com.desisuci.retrofitgsonwithrecyclerview;

import com.google.gson.annotations.SerializedName;

public class Kontak {
    @SerializedName("id")
    private Integer contactId;

    @SerializedName("nama")
    private String name;

    private String email;

    @SerializedName("nohp")
    private String phone;

    @SerializedName("alamat")
    private String address;

    public Kontak(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    //GET
    public Integer getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


    //SET
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
