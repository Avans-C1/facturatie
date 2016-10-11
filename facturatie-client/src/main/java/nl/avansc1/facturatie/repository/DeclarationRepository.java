package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.Declaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 11-10-16.
 */
@Repository
public class DeclarationRepository {
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(DeclarationRepository.class);

    /**
     *
     * @return
     */
//    @Transactional(readOnly=true)
    public List<Declaration> findAll() {
        logger.debug("findAll");

        return new ArrayList<Declaration>();
    }

    /**
     * @param declaration
     * @return
     */
    public Declaration create(final Declaration declaration) {
        logger.debug("create declaration");

        return declaration;
    }

    public void deleteDeclarationById(int id) {
        logger.debug("deleteDeclarationById");

        // delete declaration
    }
}
