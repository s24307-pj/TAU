package zjazd4;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public boolean processPayment(String userId, String productId, double amount) {
        try {
            Payment payment = new Payment();
            payment.setUserId(userId);
            payment.setProductId(productId);
            payment.setAmount(amount);
            payment.setTimestamp(LocalDateTime.now());
            payment.setStatus("SUCCESS");

            paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}