package pl.pjatk.zjazd4;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public boolean isProductAvailable(String productId) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        return inventory.isPresent() && inventory.get().getQuantity() > 0;
    }

    @Transactional
    public boolean reduceStock(String productId, int quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            if (inventory.getQuantity() >= quantity) {
                inventory.setQuantity(inventory.getQuantity() - quantity);
                inventoryRepository.save(inventory);
                return true;
            }
        }
        return false;
    }
}
