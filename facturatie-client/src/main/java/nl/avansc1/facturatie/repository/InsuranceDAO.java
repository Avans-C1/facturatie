package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.insurances.Insurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Matthijs on 13-10-2016.
 */
@Transactional
public interface InsuranceDAO extends CrudRepository<Insurance, Integer> {

}
