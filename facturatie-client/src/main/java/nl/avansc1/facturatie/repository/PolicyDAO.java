package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.customers.Customer;
import nl.avansc1.facturatie.model.insurances.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Matthijs Wilhelmus on 13-10-2016.
 *
 * This is the DAO for <code>Policy</code>.
 * The DAO is responible for CRUD on policies....
 * This DAO inherits its CRUD methods form class <code>CrudRepository</code>.
 *
 * @author Matthijs Wilhelmus
 * @version 1.0
 * @see org.springframework.data.repository.CrudRepository
 * @see Policy
 */
@Transactional
public interface PolicyDAO extends CrudRepository<Policy, Integer> {
    /**
     * Finds a policy belonging to a specific customer
     * @param customer
     * @return policy of this customer
     */
    public Policy findByCustomer(Customer customer);
}
