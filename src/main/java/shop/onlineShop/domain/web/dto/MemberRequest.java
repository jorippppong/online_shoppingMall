package shop.onlineShop.domain.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberRequest {

    @AllArgsConstructor
    @Getter
    public static class MemberRequestDTO{
        private String name;
        private String city;
        private String street;
        private String zipcode;
    }
}
