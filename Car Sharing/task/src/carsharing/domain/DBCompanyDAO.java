package carsharing.domain;

import carsharing.model.Company;

import java.util.List;
import java.util.Optional;

public class DBCompanyDAO implements CompanyDAO {
    private final DbClient dbClient;

    private static final String CREATE_TABLE_COMPANY = """
            CREATE TABLE IF NOT EXISTS COMPANY (
                ID INT PRIMARY KEY AUTO_INCREMENT,
                NAME VARCHAR(255) NOT NULL UNIQUE
            );
            """;

    private static final String FIND_ALL_COMPANIES = """
            SELECT * FROM COMPANY;
            """;

    private static final String SELECT = "SELECT * FROM COMPANY WHERE id = %d";
    private static final String INSERT_DATA = "INSERT INTO COMPANY VALUES (%d , '%s')";
    private static final String UPDATE_DATA = "UPDATE COMPANY SET name " + "= '%s' WHERE id = %d";
    private static final String DELETE_DATA = "DELETE FROM COMPANY WHERE id = %d";

    public DBCompanyDAO(String url, String user, String password) {
        dbClient = new DbClient(url, user, password);
        setup();
    }


    @Override
    public void add(Company company) {
        // determine if the company already exists
        Optional<Company> companyOptional = findById(company.getId());
        if (companyOptional.isPresent()) {
            return;
        }
        // determine the next id
        List<Company> companies = findAll();
        int nextId = companies.stream().mapToInt(Company::getId).max().orElse(0) + 1;
        company.setId(nextId);
        dbClient.run(String.format(INSERT_DATA, company.getId(), company.getName()));
    }

    @Override
    public void delete(Company company) {
        dbClient.run(String.format(DELETE_DATA, company.getId()));
    }

    @Override
    public void update(Company company) {
        dbClient.run(String.format(UPDATE_DATA, company.getName(), company.getId()));
    }

    @Override
    public Optional<Company> findById(int id) {
        return dbClient.select(String.format(SELECT, id));
    }

    @Override
    public List<Company> findAll() {
        return dbClient.selectForList(FIND_ALL_COMPANIES);
    }

    public void setup() {
        dbClient.run(CREATE_TABLE_COMPANY);
    }
}
