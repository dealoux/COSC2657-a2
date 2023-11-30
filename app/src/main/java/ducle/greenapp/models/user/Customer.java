package ducle.greenapp.models.user;

import ducle.greenapp.models.field.Reservation;
import ducle.greenapp.models.manager.Manager;

public class Customer extends User {
    private Manager<Reservation> reservationManager;

    public Customer(String id, String fName, String lName, String address, String phone, String dob, String username, String password) {
        super(id, fName, lName, address, phone, dob, username, password);
        prefixId("CUS");
        this.reservationManager = new Manager<>();
    }

    public Manager<Reservation> getReservationManager() {
        return reservationManager;
    }

    @Override
    public String print(){
        return super.print() + "; " + reservationManager.getMap().size() + reservationManager.print();
    }

    @Override
    public String toString(){
        return "Customer " + super.toString();
    }
}
