package ducle.greenapp.models;

public abstract class Entity implements Comparable<Entity> {
    protected String id;

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected void prefixId(String prefix){
        if(!id.startsWith(prefix)){
            setId(prefix + "_" + String.format("%04d", id));
        }
    }

    public String print(){
        return getId();
    }

    @Override
    public int compareTo(Entity entity) {
        return getId().compareTo(entity.getId());
    }
}
