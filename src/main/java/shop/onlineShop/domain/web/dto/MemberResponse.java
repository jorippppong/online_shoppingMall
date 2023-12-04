package shop.onlineShop.domain.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberResponse {
    @Builder
    @Getter
    public static class MemberResponseDTO {
        private String name;
        private String city;
        private String street;
        private String zipcode;
    }

    @Getter
    @Builder
    public static class MemberIdDTO {
        private Long id;
    }

    @AllArgsConstructor
    @Getter
    public static class MemberNameResponseDTO {
        private String name;
    }
}
