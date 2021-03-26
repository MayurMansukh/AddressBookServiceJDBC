package com.addressbook_jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class AddressBookDatabaseTest {

    @Test
    void readData_And_returnCount_Test() {
        AddressBookDatabase addressBookDatabase=new AddressBookDatabase();
        List<AddressBookData>addressBookData1=addressBookDatabase.readData();
        System.out.println(addressBookData1.size());
        Assertions.assertEquals(4,addressBookData1.size());
    }


}