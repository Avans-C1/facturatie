package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.Declaration;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robin Valk on 15-10-2016.
 */
@Transactional
public interface DeclarationDAO extends CrudRepository<Declaration, Integer> {

}
