package com.addressbook_jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class AddressBookDatabaseTest {

    @Test
    void readData_And_returnCount_Test() {
        AddressBookDatabase addressBookDatabase=new AddressBookDatabase();
        List<AddressBookData>addressBookData1=addressBookDatabase.readData();
        System.out.println(addressBookData1.size());
        Assertions.assertEquals(4,addressBookData1.size());
    }

    @Test
    void update_Record_into_database_returnCount_Test(){
        AddressBookDatabase addressBookDatabase=new AddressBookDatabase();
        addressBookDatabase.update_Record_into_database_returnCount();
        List<AddressBookData>payrollServiceDataList=addressBookDatabase.readData();
        Assertions.assertEquals(4,payrollServiceDataList.size());
    }
    @Test
    public void update_full_contact_details_test() throws SQLException, IllegalAccessException {
        String firstname="Mayur";
        String lastname="roy";
        String addressBookType="Friend";
        String address="vikroli";
        String city="mumbai";
        String state="MH";
        int zip=400080;
        String email="Mansukhh@gmail.in";
        String addressBookName="Book3";

        AddressBookDatabase addressBookDatabase= new AddressBookDatabase();
        addressBookDatabase.updateAddressBookDetails(firstname,lastname,addressBookType,address,city,state,zip,email,addressBookName);
        List<AddressBookData>payrollServiceDataList=addressBookDatabase.readData();
        Assertions.assertEquals(4,payrollServiceDataList.size());
    }

    @Test
    public void return_values_for_a_particular_date_range_test() throws SQLException, IllegalAccessException {
        String date="2018-01-01";
        AddressBookDatabase addressBookDatabase= new AddressBookDatabase();
        List<AddressBookData> addressBookList=addressBookDatabase.returnValuesForApaticularDateRange(date);
        Assertions.assertEquals(3,addressBookList.size());
    }
    @Test
    public void count_of_rcords_in_a_state() throws SQLException, IllegalAccessException {
        String state="MH";
        AddressBookDatabase addressBookDatabase= new AddressBookDatabase();
        String result=addressBookDatabase.countRecordsbyState(state);
        Assertions.assertEquals("3",result);
    }

    @Test
    public void insert_new_record_into_address_book_test() throws SQLException, IllegalAccessException {
        String firstname="rajan";
        String lastname="kumar";
        String addressBookType="Friend";
        String address="pune";
        String city="pune";
        String state="MH";
        int zip=9088009;
        String email="rajan@gmail.in";
        String addressBookName="Book4";
        String joiningDate="2020-01-01";

        AddressBookDatabase addressBookDatabase= new AddressBookDatabase();
        addressBookDatabase.InserNewRecord(firstname,lastname,addressBookType,address,city,state,zip,email,addressBookName,joiningDate);
        List<AddressBookData>addressBookData=addressBookDatabase.readData();
        Assertions.assertEquals(6,addressBookData.size());
    }

    @Test
    public void add_multiple_records_and_calculate_duration() throws SQLException, IllegalAccessException,SQLException {
         AddressBookDatabase addressBookDatabase=new AddressBookDatabase();
        List<AddressBookData> list=new ArrayList<>();
        list.add(new AddressBookData("raghu","khan","frd","lodh","hryana","gujrat",66677,"raoy@gmail.com","book6", "2019-03-03"));
        list.add(new AddressBookData("Rajdrea","roy","family","lodh","hryana","gujrat",66677,"raoy@gmail.com","book6", "2019-03-03"));
        Instant start =Instant.now();
        addressBookDatabase.insetRecordsUsingArrays(list);
        Instant end = Instant.now();
        System.out.println("Duration without thread "+ Duration.between(start,end));
        Instant threadstart =Instant.now();
        addressBookDatabase.insertContactUsingThread(list);
        Instant threadend = Instant.now();
        System.out.println("Duration using thread "+ Duration.between(threadstart,threadend));
        List<AddressBookData> addressBookData=addressBookDatabase.readData();
        Assertions.assertEquals(11,addressBookData.size());


    }

}