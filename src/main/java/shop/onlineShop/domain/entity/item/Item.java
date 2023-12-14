package shop.onlineShop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.onlineShop.global.exception.CustomException;
import shop.onlineShop.global.uniformApi.ErrorStatus;

import javax.persistence.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
public class Item extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    //재고 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    //재고 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity;
        if(restStock - quantity < 0){
            //TODO : error 처리
            throw new CustomException(ErrorStatus._BAD_REQUEST);
        }
        this.stockQuantity = restStock - quantity;
    }
}
