package com.addressbook_jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDatabase {
    private Connection getConnection() throws IllegalAccessException {
        String JDBCURL = "jdbc:mysql://localhost:3306/AddressBookService?useSSL=false";
        String UserName = "root";
        String Password = "P@ssw0rd1@2";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Dreiver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalAccessException(String.format("Driver not found in classpath%s", e));

        }
        try {
            System.out.println("Connecting to database" + JDBCURL);
            connection = DriverManager.getConnection(JDBCURL, UserName, Password);
            System.out.println("Connection succesfully" + connection);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return connection;
    }

    public List<AddressBookData> readData(){
        String Sql_Query = "Select * from AddressBookTable";
        List<AddressBookData> addressBookData = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Sql_Query);
            while (resultSet.next()) {
                String FirstName=resultSet.getString(1);
                String LastName=resultSet.getString(2);
                String AddressBookType=resultSet.getString(3);
                String Address=resultSet.getString(4);
                String city=resultSet.getString(5);
                String State=resultSet.getString(6);
                int Zip=resultSet.getInt(7);
                String Email=resultSet.getString(8);
                String AddressBookName=resultSet.getString(9);


                AddressBookData addressBookData1=new AddressBookData(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),
                        resultSet.getInt(7),resultSet.getString(8),resultSet.getString(9),resultSet.getDate(10));

                addressBookData.add(addressBookData1);

            }
            System.out.println(addressBookData.toString());
            statement.close();
            connection.close();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return addressBookData;
    }

    public void update_Record_into_database_returnCount(){
        String SqlQuery="update AddressBookTable set Zip=40000 where FirstName=Mayur ";
        try {
            Connection connection=this.getConnection();
            Statement statement=connection.createStatement();
            long resultset=statement.executeUpdate(SqlQuery);
        }catch (SQLException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    public void updateAddressBookDetails(String firstName, String lastName, String addressBookType, String address, String city, String state, int zip, String email, String addressBookName) throws SQLException, IllegalAccessException {
        Connection connection=this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement("Update AddressBookTable set LastName=?,AddressBookType=?,Address=?,City=?,State=?,Zip=?,Email=?,AddressBookName=? where firstname=? ; ");
            preparedStatement.setString(1,lastName);
            preparedStatement.setString(2,addressBookType);
            preparedStatement.setString(3,address);
            preparedStatement.setString(4,city);
            preparedStatement.setString(5,state);
            preparedStatement.setInt(6,zip);
            preparedStatement.setString(7,email);
            preparedStatement.setString(8,addressBookName);
            preparedStatement.setString(9,firstName);
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    public List<AddressBookData> returnValuesForApaticularDateRange(String date) throws SQLException, IllegalAccessException {
        List<AddressBookData> addressBookList=new ArrayList<>();
        Connection connection=this.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("Select * from AddressBookTable where Joining_Date>=? ; ");
            preparedStatement.setDate(1,Date.valueOf(date));
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                AddressBookData addressBookData=new AddressBookData(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getString(8),resultSet.getString(9),resultSet.getDate(10));
                addressBookList.add(addressBookData);
                connection.commit();
            }
            System.out.println(addressBookList.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
        return addressBookList;
    }


}
