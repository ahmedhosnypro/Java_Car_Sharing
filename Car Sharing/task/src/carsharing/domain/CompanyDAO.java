package carsharing.domain;

import carsharing.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDAO {
    void add(Company company);

    void delete(Company company);

    void update(Company company);

    Optional<Company> findById(int id);

    List<Company> findAll();
}
