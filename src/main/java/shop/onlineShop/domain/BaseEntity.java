package shop.onlineShop.domain;


import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseEntity {
    private LocalDate created_at;
    private LocalDate updated_at;
}
