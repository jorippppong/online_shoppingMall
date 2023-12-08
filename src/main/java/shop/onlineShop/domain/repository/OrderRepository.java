package shop.onlineShop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onlineShop.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
