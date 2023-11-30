package ducle.greenapp.models.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ducle.greenapp.models.Entity;
import ducle.greenapp.models.manager.Manager;

public class Field extends Entity {
    public final static List<String> TYPES = new ArrayList<>(
            Arrays.asList("Badminton", "Soccer", "Basketball", "Tennis", "Volleyball")
    );

    private Centre centre; // the centre this field belongs to
    private String name;
    private String type; // type of field
    private int price;
    private float width;
    private float length;
    private Manager<Reservation> reservationManager;

    public Field(String id, Centre centre, String name, String type) {
        super(id);
        prefixId("FIE");
        this.centre = centre;
        this.name = name;
        this.type = type;
        this.price = 0;
        this.width = 0f;
        this.length = 0f;
        this.reservationManager = new Manager<>();
    }

    public Field(String id, Centre centre, String name, String type, int price, float width, float length) {
        super(id);
        prefixId("FIE");
        this.centre = centre;
        this.name = name;
        this.type = type;
        this.price = price;
        this.width = width;
        this.length = length;
        this.reservationManager = new Manager<>();
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public Manager<Reservation> getReservationManager() {
        return reservationManager;
    }

    /**
     *  This method calculates and returns the area of the field
     *  */
    public float area(){
        return getWidth()* getLength();
    }

    /**
     *  This method returns true if the field is available during the given time slot on the given date
     * @param slot
     * @param date up to 7 days from today
     *  */
    public boolean isAvailable(String slot, Date date){
        boolean result = true;
        return result;
    }

    /**
     *  This method returns true if the field is available during the given time slot today
     * @param slot
     *  */
    public boolean isAvailable(String slot){
        return isAvailable(slot, new Date());
    }

    /**
     *  This method returns true if the field is available on given date
     * @param date up to 7 days from today
     *  */
    public boolean isAvailable(Date date){
        return isAvailable(null, date);
    }

    /**
     *  This method returns true if the field is available today
     *  */
    public boolean isAvailable(){
        return isAvailable(new Date());
    }

    @Override
    public String toString() {
        return "Field " + id + " - " + name + "\n"
                + "Size: " + width + "x" + length + "\n"
                + "Price: " + price;
    }

    @Override
    public String print(){
        return super.print() + "; " + name + "; " + type + "; " + price + "; " + width + "; " + length;
    }
}
