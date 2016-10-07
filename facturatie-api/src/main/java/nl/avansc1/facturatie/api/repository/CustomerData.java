package nl.avansc1.facturatie.api.repository;

import nl.avansc1.facturatie.api.model.customers.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface manages the controls of the repository of a Customer
 *
 * @author Bob van der Valk
 */
public interface CustomerData extends CrudRepository<Customer, Integer> {
    Customer findByCnc(int cnc);
}
