package AddressBookJDBC;

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
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAddressBookName() {
        return AddressBookName;
    }

    public void setAddressBookName(String addressBookName) {
        AddressBookName = addressBookName;
    }
}

