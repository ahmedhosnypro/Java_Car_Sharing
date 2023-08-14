package carsharing.model;

import carsharing.Main;

import java.util.List;

public class Company {
    private int id;
    private String name;

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }

    public List<Car> getAvailableCars() {
        return Main.DBManager.getDbCarDAO().findAllByCompanyId(id)
                .stream().filter(car -> !car.isRented()).toList();
    }
}
