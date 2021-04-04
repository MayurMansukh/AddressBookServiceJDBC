package com.addressbook_jdbc;


public class JsonServerAddressBookData {
    private int id;
    private String FirstName;
    private String LastName;
    private String AddressBookType;
    private String Address;
    private String City;
    private String State;
    private int Zip;
    private String Email;
    private String Phone;

    public JsonServerAddressBookData(int id, String firstName, String lastName, String addressBookType, String address, String city, String state, int zip, String email, String phone) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        AddressBookType = addressBookType;
        Address = address;
        City = city;
        State = state;
        Zip = zip;
        Email = email;
        Phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddressBookType() {
        return AddressBookType;
    }

    public void setAddressBookType(String addressBookType) {
        AddressBookType = addressBookType;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int zip) {
        Zip = zip;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "JsonServerAddressBookData{" +
                "id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", AddressBookType='" + AddressBookType + '\'' +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", Zip=" + Zip +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }
}
