package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.administration.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robin on 15-10-16.
 */
@Transactional
public interface UserDAO extends CrudRepository<User, Integer> {
}
