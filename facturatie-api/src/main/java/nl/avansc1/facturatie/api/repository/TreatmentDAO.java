package nl.avansc1.facturatie.api.repository;

import nl.avansc1.facturatie.api.model.billing.Treatment;
import org.springframework.data.repository.CrudRepository;

/**
 * {message}
 *
 * @author Bob van der Valk
 */
public interface TreatmentDAO extends CrudRepository<Treatment, Integer> {

}

