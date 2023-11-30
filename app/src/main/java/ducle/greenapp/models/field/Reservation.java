package ducle.greenapp.models.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ducle.greenapp.models.Entity;
import ducle.greenapp.models.user.Customer;

public class Reservation extends Entity {
    public final static List<String> STATUS_LIST = new ArrayList<>(
            Arrays.asList("Pending", "Fulfilled", "Cancelled")
    );

    public final static List<String> TIME_SLOTS = new ArrayList<>(
            Arrays.asList("7:00", "9:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00")
    );

    private Customer customer;
    private Field field;
    private String date; // up to 7 days from today
    private String timeslot;
    private String status;

    public Reservation(String id, Customer customer, Field field, String date, String timeslot) {
        super(id);
        prefixId("RES");
        this.customer = customer;
        this.field = field;
        this.date = date;
        this.timeslot = timeslot;
        this.status = STATUS_LIST.get(0);
    }

    public Reservation(String id, Customer customer, Field field, String date, String timeslot, String status){
        super(id);
        prefixId("RES");
        this.customer = customer;
        this.field = field;
        this.date = date;
        this.timeslot = timeslot;
        this.status = status;
    }


    public Customer getCustomer() {
        return customer;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public float getCost() {
        return field.getPrice();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation " + id + "\n\n"
                + customer.toString() + "\n\n"
                + field.toString() + "\n\n"
                + "Time: " + timeslot + " " + date + "\n"
                + "Status: " + status;
    }

    @Override
    public String print() {
        return super.print() + "; " + customer.getId() + "; " + field.getId() + "; " + date + "; " + timeslot + "; " + status;
    }
}
