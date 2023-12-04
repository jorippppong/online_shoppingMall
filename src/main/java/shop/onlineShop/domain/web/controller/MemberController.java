package shop.onlineShop.domain.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.onlineShop.domain.entity.Member;
import shop.onlineShop.domain.service.MemberService;
import shop.onlineShop.domain.web.dto.MemberAllResponseDTO;
import shop.onlineShop.domain.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    //1. 회원 가입
    @PostMapping()
    public ResponseEntity addMember(
            @RequestBody MemberRequestDTO memberRequestDTO
            ){
        memberService.addMember(memberRequestDTO);
        return new ResponseEntity<>("회원 가입 성공", HttpStatus.CREATED);
    }

    //2-1. 회원 단건 조회
    @GetMapping("/{memberId}")
    public ResponseEntity getMember(
            @PathVariable Long memberId
    ){
        return new ResponseEntity<>(memberService.findOneMember(memberId), HttpStatus.OK);
    }

    //2-2. 회원 전체 조회
    @GetMapping()
    public ResponseEntity getAllMembers(){
        List<Member> findMembers = memberService.findAllMembers();
        //엔티티 -> DTO 변환
        List<MemberAllResponseDTO> collect = findMembers.stream()
                .map(m -> new MemberAllResponseDTO(m.getName()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK); //TODO : 여기 name만 출력되는지 확인, 확인 후 convert로 이동해야함 (왜인지 다른 속성값들도 다 나올것 같은 이 기분)
    }

    //3. 회원 수정
    @PutMapping("/{memberId}")
    public ResponseEntity updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDTO memberRequestDTO
    ){
        return new ResponseEntity<>(memberService.updateMember(memberId, memberRequestDTO), HttpStatus.OK);
    }

    //4. 회원 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(
            @PathVariable Long memberId
    ){
        memberService.deleteMember(memberId);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }
}