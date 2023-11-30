package ducle.greenapp;

import static ducle.greenapp.models.utils.ModelUtils.readFile;
import static ducle.greenapp.models.utils.ModelUtils.saveFile;
import static ducle.greenapp.models.utils.ModelUtils.splitTrimLine;
import static ducle.greenapp.models.utils.ModelUtils.toList;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import ducle.greenapp.models.field.Centre;
import ducle.greenapp.models.field.Field;
import ducle.greenapp.models.field.Reservation;
import ducle.greenapp.models.manager.ReservationManager;
import ducle.greenapp.models.manager.UserManager;
import ducle.greenapp.models.user.Customer;
import ducle.greenapp.models.user.Owner;

public class AppRepository {
    private static AppRepository instance;
    private UserManager userManager;
    private ReservationManager reservationManager;
    private Properties configProps;

    private AppRepository(){
        userManager = new UserManager();
        reservationManager = new ReservationManager();

        initData();
//        try {
//            configProps = new Properties();
//            configProps.load(new FileInputStream("src/main/assets/config.properties"));
//            initData();
//        }
//        catch (IOException e){
//            System.out.println("Config files not found");
//            e.printStackTrace();
//        }
    }

    /**
     * This function returns the static instance of AppRepository Singleton class
     * */
    public static AppRepository Instance(){
        if(instance == null){
            instance = new AppRepository();
        }

        return instance;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public ReservationManager getReservationManager() {
        return reservationManager;
    }

    /**
     * This function reads and loads the owner input/database file
     * */
    private void initData() {
        ArrayList<String> lines = new ArrayList<>();
        readFile(lines, "/data/user/0/ducle.fieldFinder/files", "data.txt");
        ListIterator<String> iterator = lines.listIterator();

        while(iterator.hasNext()){
            String[] data = splitTrimLine(iterator.next());

            if(data.length == 0){
                continue;
            }

            String id = data[0];

            if(id.startsWith("OWN")){
                Owner owner = new Owner(id, data[1], data[2], data[3], data[4], data[5], data[6], data[7]);

                int numCentres = Integer.parseInt(data[8]);
                // add centres
                for(int i=0; i < numCentres; i++){
                    data = splitTrimLine(iterator.next());
                    Centre centre = new Centre(data[0], data[1], owner, data[2], data[3], data[4]);

                    int numFields = Integer.parseInt(data[5]);
                    // add fields
                    for(int j=0; j < numFields; j++){
                        data = splitTrimLine(iterator.next());
                        Field field = new Field(data[0], centre, data[1], data[2], Integer.parseInt(data[3]), Float.parseFloat(data[4]), Float.parseFloat(data[5]));
                        Log.d("addfield", centre.getFieldManager().add(field));
                    }

                    Log.d("addcentre", owner.getCentreManager().add(centre));
                }
                Log.d("addowner", userManager.addOwner(owner));
            }
            else if(id.startsWith("CUS")) {
                Customer customer = new Customer(id, data[1], data[2], data[3], data[4], data[5], data[6], data[7]);

                int numReservations = Integer.parseInt(data[8]);
                // add reservations
                for (int i = 0; i < numReservations; i++) {
                    data = splitTrimLine(iterator.next());

                    Field field = findField(data[2]);
                    Reservation reservation = new Reservation(data[0], customer, field, data[3], data[4], data[5]);

                    Log.d("addreservation", reservationManager.addReservation(reservation));
                }

                Log.d("addcustomer", userManager.addCustomer(customer));
            }
        }
    }

    public void storeData(){
        String data = userManager.print();
        saveFile(data,"/data/user/0/ducle.fieldFinder/files", "data.txt");
    }

    /**
     * This function returns the map of all centres
     * */
    public Map<String, Centre> centresMap(){
        Map<String, Centre> result = new HashMap<>();

        for(Owner owner: userManager.getOwnerManager().getMap().values()){
            result.putAll(owner.getCentreManager().getMap());
        }

        return result;
    }

    /**
     * This function returns the list of all centres
     * */
    public ArrayList<Centre> centresList(){
        return toList(centresMap());
    }

    /**
     * This function returns the centre with the given id
     * @param id
     * */
    public Centre findCentre(String id){
        for(Owner owner: userManager.getOwnerManager().getMap().values()){
            for(Centre centre: owner.getCentreManager().getMap().values()){
                if(centre.getId().equals(id)){
                    return centre;
                }
            }
        }
        return null;
    }

    /**
     * This function returns the map of all fields
     * */
    public Map<String, Field> fieldsMap(){
        Map<String, Field> result = new HashMap<>();

        for(Owner owner: userManager.getOwnerManager().getMap().values()){
            for(Centre centre: owner.getCentreManager().getMap().values()){
                result.putAll(centre.getFieldManager().getMap());
            }
        }

        return result;
    }

    /**
     * This function returns the list of all fields
     * */
    public ArrayList<Field> fieldsList(){
        return toList(fieldsMap());
    }

    /**
     * This function returns the field with the given id
     * @param id
     * */
    public Field findField(String id){
        for(Owner owner: userManager.getOwnerManager().getMap().values()){
            for(Centre centre: owner.getCentreManager().getMap().values()){
                for(Field field: centre.getFieldManager().getMap().values()){
                    if(field.getId().equals(id)){
                        return field;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This function returns the map of all reservations by user id
     * @param id id of the user
     * */
    public Map<String, Reservation> reservationsMap(String id){
        if(id.startsWith("CUS")){
            return userManager.getCustomerManager().get(id).getReservationManager().getMap();
        }
        else if(id.startsWith("OWN")){
            Map<String, Reservation> result = new HashMap<>();

            for(Centre centre: userManager.getOwnerManager().get(id).getCentreManager().getMap().values()){
                for(Field field: centre.getFieldManager().getMap().values()){
                    result.putAll(field.getReservationManager().getMap());
                }
            }
            return result;
        }

        return null;
    }

    /**
     * This function returns the list of all reservations by user id
     * @param id id of the user
     * */
    public ArrayList<Reservation> reservationsList(String id) {
        return toList(reservationsMap(id));
    }
}
