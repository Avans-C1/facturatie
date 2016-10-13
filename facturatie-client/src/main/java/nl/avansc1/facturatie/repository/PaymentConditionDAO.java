package nl.avansc1.facturatie.repository;

import nl.avansc1.facturatie.model.billing.PaymentCondition;

import java.util.List;

/**
 * Created by kevin on 11-10-2016.
 */
public interface PaymentConditionDAO {
    public List<PaymentCondition> getPaymentConditions();

    public PaymentCondition getPaymentCondition(int id);

    public boolean deletePaymentCondition(int id);

    public boolean addPaymentCondition(PaymentCondition paymentCondition);

    public boolean updatePaymentCondition(PaymentCondition paymentCondition);
}
