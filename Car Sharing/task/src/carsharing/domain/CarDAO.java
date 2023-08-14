package carsharing.domain;

import carsharing.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDAO {
    void add(Car car);

    void updateRented(Car car);

    Optional<Car> findById(int id);

    List<Car> findAll();

    List<Car> findAllByCompanyId(int id);
}
