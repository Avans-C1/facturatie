package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kevin on 11-10-2016.
 */
@Repository
public class InvoiceDAORepository implements InvoiceDAO {

    //Need to inject the session factory
    private SessionFactory sessionFactory;

    @Override
    public List<Invoice> getInvoices() {

        //Get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //Create query

        //Query<Invoice> theQuery =
        //        currentSession.createQuery("from Invoice", Invoice.class);

        //Execute query and get results
        //List<Invoice> invoices = theQuery.getResultList();

        //Return results
        //return invoices;
        return null;
    }

    @Override
    public Invoice getInvoice(int id) {
        Invoice invoice = (Invoice) sessionFactory.getCurrentSession().get(Invoice.class, id);
        return invoice;
    }

    @Override
    public boolean deleteInvoice(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Invoice invoice = (Invoice) session.get(Invoice.class, id);
        if (null != invoice) {
            session.delete(invoice);
            return true;
        }
        return false;
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(invoice);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(invoice);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Testcode: InvoiceDAOImpl{" +
                "sessionFactory=" + sessionFactory +
                '}';
    }
}
