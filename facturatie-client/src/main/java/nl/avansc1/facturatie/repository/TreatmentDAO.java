package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.PaymentCondition;
import nl.avansc1.facturatie.model.billing.Treatment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Pascal on 10/15/2016.
 */
public interface TreatmentDAO extends CrudRepository<Treatment, Integer> {

}
