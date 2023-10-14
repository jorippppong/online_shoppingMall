package shop.onlineShop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;  //상위 category, 스스로 매핑

    @OneToMany(mappedBy = "parent")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    List<CategoryItem> categoryItems = new ArrayList<>();
}
