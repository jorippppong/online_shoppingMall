package shop.onlineShop.domain.web.converter;

import shop.onlineShop.domain.entity.Address;
import shop.onlineShop.domain.entity.Member;
import shop.onlineShop.domain.web.dto.MemberResponse;
import shop.onlineShop.domain.web.dto.MemberRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemberConverter {
    public static Member memberRequestConverter(MemberRequest.MemberRequestDTO memberRequestDTO){
        Address address = new Address(memberRequestDTO.getCity(), memberRequestDTO.getStreet(), memberRequestDTO.getZipcode());
        return Member.builder()
                .name(memberRequestDTO.getName())
                .address(address)
                .build();
    }

    public static MemberResponse.MemberResponseDTO memberResponseConverter(Member member){
        return MemberResponse.MemberResponseDTO.builder()
                .name(member.getName())
                .city(member.getAddress().getCity())
                .street(member.getAddress().getStreet())
                .zipcode(member.getAddress().getZipcode())
                .build();
    }

    //TODO : 주소 작성 안한거 null 로 안 날아 가게 (여기 더 공부)
    public static Address addressRequestConverter(MemberRequest.MemberRequestDTO memberRequestDTO, Address address){
        String city = Optional.ofNullable(memberRequestDTO.getCity()).filter(s -> !s.isBlank()).orElse(address.getCity());
        String street = Optional.ofNullable(memberRequestDTO.getStreet()).filter(s -> !s.isBlank()).orElse(address.getStreet());
        String zipcode = Optional.ofNullable(memberRequestDTO.getZipcode()).filter(s -> !s.isBlank()).orElse(address.getZipcode());
        return new Address(city, street, zipcode);
    }

    public static MemberResponse.MemberIdDTO idResponseConverter(Long id){
        return MemberResponse.MemberIdDTO.builder()
                .id(id)
                .build();
    }

    //엔티티 -> DTO 변환
    public static List<MemberResponse.MemberNameResponseDTO> memberNameResponseDTOList(List<Member> findMembers){
        return findMembers.stream()
                .map(m -> new MemberResponse.MemberNameResponseDTO(m.getName()))
                .collect(Collectors.toList());
    }
}
