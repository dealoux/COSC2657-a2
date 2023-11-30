package ducle.greenapp.models.manager;

import java.util.ArrayList;
import java.util.Collection;

import ducle.greenapp.AppRepository;
import ducle.greenapp.models.field.Field;
import ducle.greenapp.models.field.Reservation;
import ducle.greenapp.models.user.Customer;

public class ReservationManager {
    private Manager<Reservation> manager;

    public ReservationManager(){
        manager = new Manager<>();
    }

    public Manager<Reservation> getManager() {
        return manager;
    }

    public String addReservation(Reservation reservation){
        reservation.getCustomer().getReservationManager().add(reservation);
        reservation.getField().getReservationManager().add(reservation);
        return manager.add(reservation);
    }

    public String addReservation(String id, String customerId, String fieldId, String date, String timeslot){
        Customer customer = AppRepository.Instance().getUserManager().getCustomerManager().get(customerId);
        Field field = AppRepository.Instance().fieldsMap().get(fieldId);
        Reservation reservation = new Reservation(id, customer, field, date, timeslot);
        return addReservation(reservation);
    }

    public String cancelReservation(String id){
        Reservation reservation = manager.get(id);

        if(reservation == null){
            return "Reservation " + id + " not found";
        }

        reservation.getField().getReservationManager().remove(reservation.getId());
        reservation.getCustomer().getReservationManager().remove(reservation.getId());
        manager.remove(reservation.getId());
        reservation.setStatus("Cancelled");
        return "Reservation " + reservation.getId() + " cancelled";
    }

    /**
     * This method returns the next reservation id
     * */
    public String nextReservationId(){
        return "RES_" + String.format("%04d", manager.getMap().size() + 1);
    }

    /**
     * This method creates and returns the next reservation based on the given customer and field
     * @param customer customer who made the reservation
     * @param field field that is reserved
     * */
    public Reservation nextReservation(Customer customer, Field field){
        return new Reservation(nextReservationId(), customer, field, "", "");
    }

    /**
     * This methods return a list of reservation(s) matching the given status from the given collection
     * @param reservationList reservation collection to filter
     * @param status ["Pending", "Fulfilled", "Cancelled"]
     * */
    public ArrayList<Reservation> filterReservation(Collection<Reservation> reservationList, String status){
        ArrayList<Reservation> result = new ArrayList<>();

        for(Reservation reservation : reservationList){
            if(reservation.getStatus().equalsIgnoreCase(status)){
                result.add(reservation);
            }
        }

        return result;
    }

    /**
     * This methods return a list of reservation(s) matching the given status
     * @param status ["Pending", "Fulfilled", "Cancelled"]
     * */
    public ArrayList<Reservation> filterReservation(String status){
        return filterReservation(manager.getMap().values(), status);
    }
}
