package ducle.greenapp.models.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ducle.greenapp.models.Entity;
import ducle.greenapp.models.manager.Manager;
import ducle.greenapp.models.user.Owner;

public class Centre extends Entity {
    public final static List<String> STATUS_LIST = new ArrayList<>(
            Arrays.asList("Open", "Closed")
    );

    private String name;
    private Owner owner;
    private String address;
    private String phone;
    private String status;
    private Manager<Field> fieldManager;

    public Centre(String id, String name, Owner owner, String address, String phone, String status) {
        super(id);
        prefixId("CEN");
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.fieldManager = new Manager<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Manager<Field> getFieldManager() {
        return fieldManager;
    }

    /**
     * This methods return a list of field(s) matching the given type and/or slots from the given collection
     * @param fieldList field collection to filter
     * @param type ["Badminton", "Soccer", "Basketball", "Tennis", "Volleyball"]
     * @param slot ["7:00", "9:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00"]
     * @param date up to 7 days from today
     * */
    public List<Field> filterFields(Collection<Field> fieldList, String type, String slot, Date date){
        List<Field> result = new ArrayList<>();

        for(Field field : fieldList){
            if(field.getType().equalsIgnoreCase(type)){
                if(slot != null){
                    if(field.isAvailable(slot, date)){
                        result.add(field);
                    }
                }
                else{
                    if(date != new Date()){
                        if(field.isAvailable(date)){
                            result.add(field);
                        }
                    }
                    else{
                        result.add(field);
                    }
                }
            }
            else {
                if(slot != null){
                    if(field.isAvailable(slot, date)){
                        result.add(field);
                    }
                }
                else{
                    if(date != new Date()){
                        if(field.isAvailable(date)){
                            result.add(field);
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * This methods return a list of field(s) matching the given type and/or slots
     * @param type ["Badminton", "Soccer", "Basketball", "Tennis", "Volleyball"]
     * @param slot ["7:00", "9:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00"]
     * @param date up to 7 days from today
     * */
    public List<Field> filterFields(String type, String slot, Date date){
        return filterFields(getFieldManager().getMap().values(), type, slot, date);
    }

    @Override
    public String toString() {
        return "Centre " + id + " - " + name + "\n"
                + "Address: " + address + "\n"
                + "Phone: " + phone + "\n"
                + "Status: " + status;
    }

    @Override
    public String print(){
        return super.print() + "; " + name + "; " + address + "; " + phone + "; " + status + "; " + fieldManager.getMap().size() + fieldManager.print();
    }
}
