package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.administration.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Robin on 15-10-16.
 *
 * @author Robin Valk
 * @version 1.0
 * @see User
 * @see CrudRepository
 */
@Transactional
public interface UserDAO extends CrudRepository<User, Integer> {

    /**
     * Find user by email
     * Used for login.
     *
     * @param email
     * @return
     */
    public User findByEmail(String email);

}
