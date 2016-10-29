package nl.avansc1.facturatie.api.repository;

import nl.avansc1.facturatie.api.model.billing.Declaration;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * {message}
 *
 * @author Bob van der Valk
 */
@Transactional
public interface DeclarationDAO extends CrudRepository<Declaration, Integer> {

}
