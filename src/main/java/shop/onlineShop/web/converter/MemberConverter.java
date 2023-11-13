package shop.onlineShop.web.converter;

import shop.onlineShop.domain.Address;
import shop.onlineShop.domain.Member;
import shop.onlineShop.web.dto.MemberRequestDTO;
import shop.onlineShop.web.dto.MemberResponseDTO;

public class MemberConverter {
    public static Member memberRequestConverter(MemberRequestDTO memberRequestDTO){
        Address address = new Address(memberRequestDTO.getCity(), memberRequestDTO.getStreet(), memberRequestDTO.getZipcode());
        return Member.builder()
                .name(memberRequestDTO.getName())
                .address(address)
                .build();
    }

    public static MemberResponseDTO memberResponseConverter(Member member){
        return MemberResponseDTO.builder()
                .name(member.getName())
                .city(member.getAddress().getCity())
                .street(member.getAddress().getStreet())
                .zipcode(member.getAddress().getZipcode())
                .build();
    }

    public static Address addressRequestConverter(MemberRequestDTO memberRequestDTO){
        return new Address(memberRequestDTO.getCity(), memberRequestDTO.getStreet(), memberRequestDTO.getZipcode());
    }
}
