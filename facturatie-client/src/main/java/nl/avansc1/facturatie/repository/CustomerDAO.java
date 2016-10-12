package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.customers.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * This controls the hibernate stuff of a customer
 *
 * @author Bob van der Valk
 */
@Transactional
public interface CustomerDAO extends CrudRepository<Customer, Integer> {
    public Customer findByCsn(int csn);
}
