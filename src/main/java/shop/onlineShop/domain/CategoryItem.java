package shop.onlineShop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CategoryItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
