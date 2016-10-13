package nl.avansc1.facturatie.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import nl.avansc1.facturatie.model.insurances.Policy;
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
import java.sql.Date;
import java.util.List;

/**
 * Created by Matthijs on 10-10-2016.
 */
@Repository
public class PolicyRepository {
    private final Logger logger = LoggerFactory.getLogger(PolicyRepository.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Deze constructor wordt aangeroepen vanuit de config/PersistenceContext class.
    public PolicyRepository(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    /**
     *
     * @return
     */
    @Transactional(readOnly=true)
    public List<Policy> findAll() {
        logger.info("findAll");
        List<Policy> result = jdbcTemplate.query("SELECT * FROM policy", new PolicyRowMapper());
        logger.info("found " + result.size() + " policies");
        return result;
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional(readOnly=true)
    public Policy getId(int id) {
        logger.info("findPolicyById");
        return jdbcTemplate.queryForObject(
                "SELECT * FROM policy WHERE PolicyID=?",
                new Object[]{id}, new PolicyRowMapper());
    }

    /**
     *
     * @param policy
     * @return
     */
    public Policy create(final Policy policy) {

        logger.debug("create repository = " + policy.getId());

        final String sql = "INSERT INTO policy(`contribution`, `active`, `contributionUsed`) " +
                "VALUES(?,?,?)";

        // KeyHolder gaat de auto increment key uit de database bevatten.
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setFloat(1, policy.getContribution());
                ps.setBoolean(4, policy.isActive());
                ps.setFloat(5, policy.getContributionUsed());
                return ps;
            }
        }, holder);

        // Zet de auto increment waarde in de Policy
        int newPolicyId = holder.getKey().intValue();
        policy.setId(newPolicyId);
        return policy;
    }

    public void deletePolicyById(int id) {
        logger.debug("deletePolicyById");
        jdbcTemplate.update("DELETE FROM policy WHERE PolicyID=?", new Object[]{id});
    }

}
