package com.addressbook_jdbc;

public class AddressBookData {
    public String FirstName;
    public String LastName;
    public String AddressBookType;
    public String Address;
    public String city;
    public String State;
    public int Zip;
    public String Email;
    public String AddressBookName;


    public AddressBookData(String firstName, String lastName, String addressBookType, String address, String city, String state, int zip, String email, String addressBookName) {
        FirstName = firstName;
        LastName = lastName;
        AddressBookType = addressBookType;
        Address = address;
        this.city = city;
        State = state;
        Zip = zip;
        Email = email;
        AddressBookName = addressBookName;
    }


}

