package com.addressbook_jdbc;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


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
}