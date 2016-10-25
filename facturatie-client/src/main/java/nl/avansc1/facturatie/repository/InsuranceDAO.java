package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.insurances.Insurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Matthijs Wilhelmus on 13-10-2016.
 *
 * This is the DAO for <code>Insurance</code>.
 * The DAO is responible for CRUD on insurances....
 * This DAO inherits its CRUD methods form class <code>CrudRepository</code>.
 *
 * @author Matthijs Wilhelmus
 * @version 1.0
 * @see org.springframework.data.repository.CrudRepository
 * @see Insurance
 */
@Transactional
public interface InsuranceDAO extends CrudRepository<Insurance, Integer> {

}
