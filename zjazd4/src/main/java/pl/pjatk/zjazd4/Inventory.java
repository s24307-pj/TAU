package pl.pjatk.zjazd4;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Inventory {

    @Id
    private String productId;
    private int quantity;

}