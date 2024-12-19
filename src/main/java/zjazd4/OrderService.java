package zjazd4;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;

    public OrderService(PaymentService paymentService, InventoryService inventoryService, NotificationService notificationService) {
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
    }

    @Transactional
    public String placeOrder(String userId, String productId, double amount, String email) {
        if (!inventoryService.isProductAvailable(productId)) {
            return "Product is not available in inventory.";
        }

        boolean stockReduced = inventoryService.reduceStock(productId, 1);
        if (!stockReduced) {
            return "Failed to update inventory.";
        }

        boolean paymentProcessed;
        try {
            paymentProcessed = paymentService.processPayment(userId, productId, amount);
        } catch (Exception e) {
            return "Payment processing failed due to an error.";
        }

        if (!paymentProcessed) {
            return "Payment failed.";
        }

        boolean notificationSent = notificationService.sendNotification(
                email, "Order Confirmation", "Your order has been placed successfully!"
        );

        if (!notificationSent) {
            return "Order placed, but failed to send notification.";
        }

        return "Order placed successfully.";
    }
}