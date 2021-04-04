package com.addressbook_jdbc;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;


class JsonServerAddressBookDataTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4000;
    }

    public JsonServerAddressBookData[] getContactList() {
        Response response = RestAssured.get("/AddressBook");
        System.out.println("Contact entries in Json Server: \n" + response.asString());
        JsonServerAddressBookData[] jsonServerAddressBookData = new Gson().fromJson(response.asString(), JsonServerAddressBookData[].class);
        return jsonServerAddressBookData;

    }

    public Response addContactInJsonServer(JsonServerAddressBookData restAssuredContactData) {
        String Contact = new Gson().toJson(restAssuredContactData);
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.body(Contact);
        return requestSpecification.post("/AddressBook");

    }

    @Test
    public void givenContactDateToGsonServer_shouldRetrieveServerData() {
        JsonServerAddressBookData[] restAssureContactData = getContactList();
        System.out.println(restAssureContactData);
        Assertions.assertEquals(3, restAssureContactData.length);
    }
    @Test
    public void addMultipleContact_shouldReturn_201ResponseCode(){
        JsonServerAddressBookData[] restAssureContactData=getContactList();
        JsonServerAddressBookData jsonServerAddressBookData1=new JsonServerAddressBookData(4,"raghu","juyal","Friend","alhabad","bhihar","UP",45367,"Raghu@gmail.com","8899776655");
        Response response=addContactInJsonServer(jsonServerAddressBookData1);
        int statusCode= response.statusCode();
        Assertions.assertEquals(201,statusCode);
        Assertions.assertEquals(4,restAssureContactData.length);
    }
    @Test
    public void addNewsalary_ShouldRetun_200ResponseCode() throws SQLException {
        JsonServerAddressBookData[] restAssureEmployeeData=getContactList();
        String ContactJson=new Gson().toJson(restAssureEmployeeData);
        Assertions.assertEquals(4,restAssureEmployeeData.length);
        RequestSpecification requestSpecification=RestAssured.given();
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body("{\"FirstName\":\"Rahul\",\"Address\":\"Nerul\"}");
        Response response=requestSpecification.put("/AddressBook/update/2");

        int statusCode=response.getStatusCode();
        Assertions.assertEquals(200,statusCode);
    }

}