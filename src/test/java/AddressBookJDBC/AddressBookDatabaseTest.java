package AddressBookJDBC;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
class AddressBookDatabaseTest {

    @Test
    void readData_And_ShowData() {

        AddressBookDatabase addressBookDatabase=new AddressBookDatabase();
        List<AddressBookData>addressBookData1=addressBookDatabase.readData();
        System.out.println(addressBookData1.size());
        Assertions.assertEquals(4,addressBookData1.size());

    }

    @Test
    void UpdateTable(){
        AddressBookDatabase addressBookDatabase=new AddressBookDatabase();
        addressBookDatabase.Update();
        List<AddressBookData>payrollServiceDataList=addressBookDatabase.readData();
        Assertions.assertEquals(4,payrollServiceDataList.size());
    }
}