package com.addressbook_jdbc;

import jdk.nashorn.internal.ir.WhileNode;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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
                        resultSet.getInt(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10));

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
                AddressBookData addressBookData=new AddressBookData(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10));
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

    public String countRecordsbyState(String state) throws SQLException, IllegalAccessException {
        Connection connection=this.getConnection();
        String result=null;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("select count(*) from AddressBookTable where state=? ; ");
            preparedStatement.setString(1,state);
            ResultSet resultSet=preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                result=resultSet.getString(1);
                System.out.println(resultSet.getString(1));
            }
            return result;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
        return result;

    }

    public void InserNewRecord(String firstName, String lastName, String addressBookType, String address, String city, String state, int zip, String email, String addressBookName, String joiningDate) throws SQLException, IllegalAccessException {
        Connection connection=this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement("insert into AddressBookTable(FirstName,LastName,AddressBookType,Address,City,State,Zip,Email,AddressBookName,Joining_Date) values (?,?,?,?,?,?,?,?,?,?); ");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,addressBookType);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,city);
            preparedStatement.setString(6,state);
            preparedStatement.setInt(7,zip);
            preparedStatement.setString(8,email);
            preparedStatement.setString(9,addressBookName);
            preparedStatement.setDate(10,  Date.valueOf(joiningDate));
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    public void insetRecordsUsingArrays(List<AddressBookData> addressBookData) throws SQLException, IllegalAccessException {
        Connection connection = this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement("insert into AddressBookTable(FirstName,LastName,AddressBookType,Address,City,State,Zip,Email,AddressBookName,Joining_Date) values (?,?,?,?,?,?,?,?,?,?); ");
            for (Iterator<AddressBookData> iterator = addressBookData.iterator(); ((Iterator<?>) iterator).hasNext(); ) {
                AddressBookData addressBookData1 = (AddressBookData) iterator.next();
                System.out.println("contact being added  " + addressBookData1.FirstName);
                preparedStatement.setString(1, addressBookData1.getFirstName());
                preparedStatement.setString(2, addressBookData1.getLastName());
                preparedStatement.setString(3, addressBookData1.getAddressBookType());
                preparedStatement.setString(4, addressBookData1.getAddress());
                preparedStatement.setString(5, addressBookData1.getCity());
                preparedStatement.setString(6, addressBookData1.getState());
                preparedStatement.setInt(7,addressBookData1.getZip());
                preparedStatement.setString(8,addressBookData1.getEmail());
                preparedStatement.setString(9,addressBookData1.getAddressBookName());
                preparedStatement.setString(10,addressBookData1.getJoiningDate());

                System.out.println("employee Added  " + addressBookData1.FirstName);
                preparedStatement.addBatch();
            }
            int[] recordUpdateCounts = preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    public void insertContactUsingThread(List<AddressBookData>addressBookData){
        Map<Integer,Boolean>addressBookStatus=new HashMap<>();
        addressBookData.forEach(addressBookData1 -> {
            Runnable task= () -> {
                addressBookStatus.put(addressBookData.hashCode(),false);
                System.out.println("contact beging added "+Thread.currentThread().getName());
                addressBookStatus.put(addressBookData.hashCode(),true);
            };
            Thread thread =new Thread(task,addressBookData1.FirstName);
            thread.start();
        });
        while (addressBookStatus.containsValue(false)){
            try {
                Thread.sleep(10);
            }catch ( InterruptedException e){

            }
        }
    }
}
