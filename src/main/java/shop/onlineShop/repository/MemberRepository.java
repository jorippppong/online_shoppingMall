package shop.onlineShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onlineShop.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
