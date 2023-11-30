package ducle.greenapp.models.user;

import ducle.greenapp.models.Entity;

public class User extends Entity {
    protected String fName;
    protected String lName;
    protected String address;
    protected String phone;
    protected String dob;
    protected String username;
    protected String password;

    public User(String id, String fName, String lName, String address, String phone, String dob, String username, String password) {
        super(id);
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id + " " + fName + " " + lName + "\n"
                + "Address: " + address + "\n"
                + "Phone: " + phone;
    }

    @Override
    public String print(){
        return super.print() + "; " + fName + "; " + lName + "; " + address + "; " + phone + "; " + dob + "; " + username + "; " + password;
    }
}
