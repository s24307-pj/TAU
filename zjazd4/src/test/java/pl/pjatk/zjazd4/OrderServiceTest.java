package pl.pjatk.zjazd4;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrderServiceTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder_Successful() {
        String userId = "user1";
        String productId = "product1";
        double amount = 100.0;
        String email = "user1@user1.com";

        when(inventoryService.isProductAvailable(productId)).thenReturn(true);
        when(inventoryService.reduceStock(productId, 1)).thenReturn(true);
        when(paymentService.processPayment(userId, productId, amount)).thenReturn(true);
        when(notificationService.sendNotification(email, "Order Confirmation", "Your order has been placed successfully!")).thenReturn(true);

        String result = orderService.placeOrder(userId, productId, amount, email);

        assertEquals("Order placed successfully.", result);
        verify(inventoryService).isProductAvailable(productId);
        verify(inventoryService).reduceStock(productId, 1);
        verify(paymentService).processPayment(userId, productId, amount);
        verify(notificationService).sendNotification(email, "Order Confirmation", "Your order has been placed successfully!");
    }

    @Test
    void testPlaceOrder_ProductNotAvailable() {
        String userId = "user1";
        String productId = "product1";
        double amount = 100.0;
        String email = "user1@user1.com";

        when(inventoryService.isProductAvailable(productId)).thenReturn(false);

        String result = orderService.placeOrder(userId, productId, amount, email);

        assertEquals("Product is not available in inventory.", result);
        verify(inventoryService).isProductAvailable(productId);
        verifyNoInteractions(paymentService);
        verifyNoInteractions(notificationService);
    }

    @Test
    void testPlaceOrder_StockReductionFails() {
        String userId = "user1";
        String productId = "product1";
        double amount = 100.0;
        String email = "user1@user1.com";

        when(inventoryService.isProductAvailable(productId)).thenReturn(true);
        when(inventoryService.reduceStock(productId, 1)).thenReturn(false);

        String result = orderService.placeOrder(userId, productId, amount, email);

        assertEquals("Failed to update inventory.", result);
        verify(inventoryService).isProductAvailable(productId);
        verify(inventoryService).reduceStock(productId, 1);
        verifyNoInteractions(paymentService);
        verifyNoInteractions(notificationService);
    }

    @Test
    void testPlaceOrder_PaymentFails() {
        String userId = "user1";
        String productId = "product1";
        double amount = 100.0;
        String email = "user1@user1.com";

        when(inventoryService.isProductAvailable(productId)).thenReturn(true);
        when(inventoryService.reduceStock(productId, 1)).thenReturn(true);
        when(paymentService.processPayment(userId, productId, amount)).thenReturn(false);

        String result = orderService.placeOrder(userId, productId, amount, email);

        assertEquals("Payment failed.", result);
        verify(inventoryService).isProductAvailable(productId);
        verify(inventoryService).reduceStock(productId, 1);
        verify(paymentService).processPayment(userId, productId, amount);
        verifyNoInteractions(notificationService);
    }

    @Test
    void testPlaceOrder_PaymentThrowsException() {
        String userId = "user1";
        String productId = "product1";
        double amount = 100.0;
        String email = "user1@user1.com";

        when(inventoryService.isProductAvailable(productId)).thenReturn(true);
        when(inventoryService.reduceStock(productId, 1)).thenReturn(true);
        when(paymentService.processPayment(userId, productId, amount)).thenThrow(new RuntimeException("Payment Service Error"));

        String result = orderService.placeOrder(userId, productId, amount, email);

        assertEquals("Payment processing failed due to an error.", result);
        verify(inventoryService).isProductAvailable(productId);
        verify(inventoryService).reduceStock(productId, 1);
        verify(paymentService).processPayment(userId, productId, amount);
        verifyNoInteractions(notificationService);
    }
}
