package carsharing.domain;

import carsharing.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    void add(Customer customer);

    Optional<Customer> findById(int id);

    List<Customer> findAll();

    boolean rentCar(Customer customer, int carId);

    boolean returnCar(Customer customer);
}
