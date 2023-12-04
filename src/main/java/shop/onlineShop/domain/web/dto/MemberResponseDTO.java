package shop.onlineShop.domain.web.dto;

import lombok.Builder;

@Builder
public class MemberResponseDTO {
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
