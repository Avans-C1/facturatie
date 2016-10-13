package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.insurances.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Matthijs on 13-10-2016.
 */
@Transactional
public interface PolicyDAO extends CrudRepository<Policy, Integer> {
    public Policy findbyId(int id);
}
