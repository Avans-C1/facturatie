package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.Invoice;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kevin on 11-10-2016.
 */
@Transactional
public interface InvoiceDAO extends CrudRepository<Invoice, Integer> {

}
