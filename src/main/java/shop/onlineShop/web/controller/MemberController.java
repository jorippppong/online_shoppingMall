package shop.onlineShop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.onlineShop.service.MemberService;
import shop.onlineShop.web.dto.MemberRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity addMember(
            @RequestBody MemberRequestDTO memberRequestDTO
            ){
        memberService.addMember(memberRequestDTO);
        return new ResponseEntity<>("회원 가입 성공", HttpStatus.CREATED);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity getMember(
            @PathVariable Long memberId
    ){
        return new ResponseEntity<>(memberService.getMember(memberId), HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDTO memberRequestDTO
    ){
        return new ResponseEntity<>(memberService.updateMember(memberId, memberRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(
            @PathVariable Long memberId
    ){
        memberService.deleteMember(memberId);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }
}
