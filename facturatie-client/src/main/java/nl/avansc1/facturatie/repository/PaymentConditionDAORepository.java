package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.PaymentCondition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by kevin on 11-10-2016.
 */
public class PaymentConditionDAORepository implements PaymentConditionDAO {

    //Need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<PaymentCondition> getPaymentConditions() {

        //Get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //Create query

        //Query<PaymentCondition> theQuery =
        //        currentSession.createQuery("from PaymentCondition", PaymentCondition.class);

        //Execute query and get results
        //List<PaymentCondition> paymentConditions = theQuery.getResultList();

        //Return results
        //return paymentConditions;
        return null;
    }

    @Override
    public PaymentCondition getPaymentCondition(int id) {
        PaymentCondition paymentCondition = (PaymentCondition) sessionFactory.getCurrentSession().get(PaymentCondition.class, id);
        return paymentCondition;
    }

    @Override
    public boolean deletePaymentCondition(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        PaymentCondition paymentCondition = (PaymentCondition) session.get(PaymentCondition.class, id);
        if (null != paymentCondition) {
            session.delete(paymentCondition);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPaymentCondition(PaymentCondition paymentCondition) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(paymentCondition);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updatePaymentCondition(PaymentCondition paymentCondition) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(paymentCondition);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
   
}
