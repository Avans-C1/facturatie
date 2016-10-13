package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.Vat;
import nl.avansc1.facturatie.model.customers.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Pascal van Hoof on 13-10-2016.
 */

@Transactional
public interface VatDAO extends CrudRepository<Vat, Integer> {

}


