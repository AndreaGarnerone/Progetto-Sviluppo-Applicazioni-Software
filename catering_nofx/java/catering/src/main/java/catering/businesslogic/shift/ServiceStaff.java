package catering.businesslogic.shift;

public class ServiceStaff {
    private String name;
    private int id;

    public ServiceStaff(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ServiceStaff{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
