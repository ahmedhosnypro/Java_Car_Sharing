package carsharing.domain;

import carsharing.model.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBCarDAO implements CarDAO {
    private final DbClient dbClient;

    private static final String CREATE_TABLE_CAR = """
            CREATE TABLE IF NOT EXISTS car (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR UNIQUE NOT NULL,
                company_id INT NOT NULL,
                CONSTRAINT fk_company FOREIGN KEY (company_id)
                REFERENCES COMPANY(id)
            );
            """;

    private static final String FIND_ALL_CARS = "SELECT * FROM car;";

    // car
    private static final String SELECT = "SELECT * FROM car WHERE id = %d";
    private static final String INSERT_DATA = "INSERT INTO car VALUES (%d , '%s', %d)";
    private static final String UPDATE_DATA = "UPDATE car SET name = '%s', company_id = %d WHERE id = %d";
    private static final String DELETE_DATA = "DELETE FROM car WHERE id = %d";

    public DBCarDAO(DbClient dbClient) {
        this.dbClient = dbClient;
        setup();
    }

    public void add(Car car) {
        // determine if the company already exists
        Optional<Car> carOptional = findById(car.getId());
        if (carOptional.isPresent()) {
            return;
        }
        // determine the next id
        List<Car> cars = findAll();
        int nextId = cars.stream().mapToInt(Car::getId).max().orElse(0) + 1;
        car.setId(nextId);
        dbClient.run(String.format(INSERT_DATA, car.getId(), car.getName(), car.getCompanyId()));
    }

    public void delete(Car car) {
        dbClient.run(String.format(DELETE_DATA, car.getId()));
    }

    public void update(Car car) {
        dbClient.run(String.format(UPDATE_DATA, car.getName(), car.getCompanyId(), car.getId()));
    }

    public Optional<Car> findById(int id) {
        List<Car> items = selectForList(String.format(SELECT, id));
        if (items.size() == 1) {
            return Optional.ofNullable(items.get(0));
        } else if (items.isEmpty()) {
            return Optional.empty();
        } else {
            throw new IllegalStateException("Query returned more than one object");
        }
    }

    @Override
    public List<Car> findAll() {
        return selectForList(FIND_ALL_CARS);
    }

    @Override
    public List<Car> findAllByCompanyId(int id) {
        return selectForList(String.format("SELECT * FROM car WHERE company_id = %d", id));
    }

    public void setup() {
        dbClient.run(CREATE_TABLE_CAR);
    }

    private List<Car> selectForList(String query) {
        List<Car> cars = new ArrayList<>();

        try (Connection con = dbClient.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSetItem = statement.executeQuery(query)
        ) {
            con.setAutoCommit(true);
            while (resultSetItem.next()) {
                int id = resultSetItem.getInt("id");
                String name = resultSetItem.getString("name");
                int companyId = resultSetItem.getInt("company_id");
                cars.add(new Car(id, name, companyId));
            }
            return cars;
        } catch (Exception e) {
            System.out.println("Error while executing statement: " + query + " " + e.getMessage());
        }
        return cars;
    }
}
