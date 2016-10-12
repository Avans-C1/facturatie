package nl.avansc1.facturatie.api.model.customers;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * {message}
 *
 * @author Bob van der Valk
 */
@Transactional
public interface CustomerDao extends CrudRepository<Customer, Integer> {
    public Customer findByCsn(int csn);
}
