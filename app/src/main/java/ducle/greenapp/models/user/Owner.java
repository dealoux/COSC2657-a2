package ducle.greenapp.models.user;

import ducle.greenapp.models.field.Centre;
import ducle.greenapp.models.manager.Manager;

public class Owner extends User{
    private Manager<Centre> centreManager;

    public Owner(String id, String fName, String lName, String address, String phone, String dob, String username, String password) {
        super(id, fName, lName, address, phone, dob, username, password);
        prefixId("OWN");
        this.centreManager = new Manager<>();
    }

    public Manager<Centre> getCentreManager() {
        return centreManager;
    }

    @Override
    public String print(){
        return super.print() + "; " + centreManager.getMap().size() + centreManager.print();
    }

    @Override
    public String toString(){
        return "Owner " + super.toString();
    }
}
