package ducle.greenapp.models.manager;

import static ducle.greenapp.models.utils.ModelUtils.toList;

import ducle.greenapp.models.Entity;

import java.util.*;

public class Manager<T extends Entity> {
    protected Map<String, T> map;

    public Manager(){
        map = new HashMap<>();
    }

    public Map<String, T> getMap() {
        return map;
    }

    /**
     * This function returns a list of all T instances
     * */
    public ArrayList<T> getList(){
        return toList(map);
    }

    /**
     * This function searches all maps to try and find the entity with the given id.
     * Returns the Entity instance if found, otherwise returns null
     * @param id id for searching
     * */
    public T get(String id){
        return map.get(id);
    }

    /**
     * This function adds the given Entity instance to the map.
     * Returns a string indicating the result of the operation
     * @param entity reference to the Entity instance
     * */
    public String add(T entity){
        map.put(entity.getId(), entity);
        return "Added " + entity.getClass().getSimpleName() + " " + entity.getId();
    }

    /**
     * This function tries to remove the entity with the given id from the map if found.
     * Returns a string indicating the result of the operation
     * @param id id of the entity to be removed
     * */
    public String remove(String id){
        String result;
        Entity entity = map.remove(id);

        if(entity != null){
            result = "Removed " + entity.getClass().getSimpleName() + " " + entity.print();
        }
        else{
            result = "Could not find any " + entity.getClass().getSimpleName() + " with id " + id;
        }

        return result;
    }

    /**
     * This function tries to remove the given Entity instance from the map.
     * Returns a string indicating the result of the operation
     * @param entity reference of the Entity to be removed
     * */
    public String remove(T entity){
        return remove(entity.getId());
    }

    public String toString(){
        String result = "";

        List<T> entities = getList();
        for(T entity : entities){
            result +=  "\n" + entity.toString();
        }

        return result;
    }

    public String print(){
        String result = "";

        for(T entity : getList()){
            result += "\n" + entity.print();
        }

        return result;
    }
}
