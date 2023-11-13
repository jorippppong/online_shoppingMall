package shop.onlineShop.web.dto;

import lombok.Builder;
import shop.onlineShop.domain.Member;

@Builder
public class MemberResponseDTO {
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
