package shop.onlineShop.domain;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Book extends Item{
    private String author;
    private String isbn;
}