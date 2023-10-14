package shop.onlineShop.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
public class Album extends Item{
    private String artist;
    private String etc;
}
