package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.insurances.Insurance;
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
public class InsuranceRepository {
    private final Logger logger = LoggerFactory.getLogger(InsuranceRepository.class);;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Deze constructor wordt aangeroepen vanuit de config/PersistenceContext class.
    public InsuranceRepository(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    /**
     *
     * @return
     */
    @Transactional(readOnly=true)
    public List<Insurance> findAll() {
        logger.info("findAll");
        List<Insurance> result = jdbcTemplate.query("SELECT * FROM insurance", new InsuranceRowMapper());
        logger.info("found " + result.size() + " insurances");
        return result;
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional(readOnly=true)
    public Insurance findInsuranceById(int id) {
        logger.info("findMemberById");
        return jdbcTemplate.queryForObject(
                "SELECT * FROM member WHERE MemberID=?",
                new Object[]{id}, new InsuranceRowMapper());
    }

    /**
     *
     * @param insurance
     * @return
     */
    public Insurance create(final Insurance insurance) {

        logger.debug("create repository = " + insurance.getName());

        final String sql = "INSERT INTO insurance(`Name`, `MonthlyFee`, `CoveredTreatments`) " +
                "VALUES(?,?,?)";

        // KeyHolder gaat de auto increment key uit de database bevatten.
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, insurance.getName());
                ps.setInt(3, insurance.getCoveredTreatments());
                ps.setFloat(2, insurance.getMonthlyFee());
                return ps;
            }
        }, holder);

        // Zet de auto increment waarde in de Member
        int newInsuranceId = holder.getKey().intValue();
        insurance.setId(newInsuranceId);
        return insurance;
    }

    public void deleteInsuranceById(int id) {
        logger.debug("deleteInsuranceById");
        jdbcTemplate.update("DELETE FROM insurance WHERE insuranceID=?", new Object[]{id});
    }
}
