package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.Declaration;
import nl.avansc1.facturatie.model.customers.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Robin Valk on 15-10-2016.
 */
@Transactional
public interface DeclarationDAO extends CrudRepository<Declaration, Integer> {
    public List<Declaration> findByCustomer(Customer customer);
}
