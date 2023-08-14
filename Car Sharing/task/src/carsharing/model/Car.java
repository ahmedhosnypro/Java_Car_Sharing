package carsharing.model;

public class Car {
    private int id;
    private String name;
    private final int companyId;
    private boolean rented = false;

    public Car(int id, String name, int companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
    }

    public Car(int id, String name, int companyId, boolean rented) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.rented = rented;
    }

    public Car(String name, int companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}