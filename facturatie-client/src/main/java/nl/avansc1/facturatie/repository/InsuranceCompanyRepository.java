package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Matthijs on 10-10-2016.
 */
@Repository
public class InsuranceCompanyRepository {
    private final Logger logger = LoggerFactory.getLogger(InsuranceCompanyRepository.class);;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Deze constructor wordt aangeroepen vanuit de config/PersistenceContext class.
    public InsuranceCompanyRepository(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    /**
     *
     * @return
     */
    @Transactional(readOnly=true)
    public List<InsuranceCompany> findAll() {
        logger.info("findAll");
        List<InsuranceCompany> result = jdbcTemplate.query("SELECT * FROM insurance", new InsuranceCompanyRowMapper());
        logger.info("found " + result.size() + " insurances");
        return result;
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional(readOnly=true)
    public InsuranceCompany findInsuranceCompanyById(int id) {
        logger.info("findMemberById");
        return jdbcTemplate.queryForObject(
                "SELECT * FROM member WHERE MemberID=?",
                new Object[]{id}, new InsuranceCompanyRowMapper());
    }

    /**
     *
     * @param insuranceCompany
     * @return
     */
    public InsuranceCompany create(final InsuranceCompany insuranceCompany) {

        logger.debug("create repository = " + insuranceCompany.getName());

        final String sql = "INSERT INTO insuranceCompany(`Name`, `HouseNumber`, `zipcode`, `city`, `PhoneNumber`, `email`, `KvKNumber`) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        // KeyHolder gaat de auto increment key uit de database bevatten.
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, insuranceCompany.getName());
                ps.setString(1, insuranceCompany.getCity());
                ps.setString(1, insuranceCompany.getEmail());
                ps.setString(1, insuranceCompany.getHouseNumber());
                ps.setString(1, insuranceCompany.getZipcode());
                ps.setInt(2, insuranceCompany.getKvkNumber());
                ps.setInt(3, insuranceCompany.getPhoneNumber());
                return ps;
            }
        }, holder);

        // Zet de auto increment waarde in de Member
        int newInsuranceCompanyId = holder.getKey().intValue();
        insuranceCompany.setId(newInsuranceCompanyId);
        return insuranceCompany;
    }

    public void deleteInsuranceCompanyById(int id) {
        logger.debug("deleteInsuranceById");
        jdbcTemplate.update("DELETE FROM insurance WHERE insuranceID=?", new Object[]{id});
    }
}

