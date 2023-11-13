package shop.onlineShop.web.dto;

import lombok.Getter;

@Getter
public class MemberRequestDTO {
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
