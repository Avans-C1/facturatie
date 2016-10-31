package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.Declaration;
import nl.avansc1.facturatie.model.customers.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Robin Valk on 15-10-2016.
 */
@Transactional
public interface DeclarationDAO extends CrudRepository<Declaration, Integer> {
    public List<Declaration> findByCustomer(Customer customer);

    @Query(
            value = "SELECT * FROM declarations WHERE customer_id = :Customer_Id AND (invoice_id IS NULL OR invoice_id = 0)",
            nativeQuery = true)
    public List<Declaration> findByCustomerInvoice(@Param("Customer_Id") int Id);

    public List<Declaration> findByCustomerAndInvoiceNotNullOrderById(Customer customer);
}
