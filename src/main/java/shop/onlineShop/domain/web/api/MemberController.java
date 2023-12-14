package shop.onlineShop.domain.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.onlineShop.domain.entity.Member;
import shop.onlineShop.domain.service.MemberService;
import shop.onlineShop.domain.web.converter.MemberConverter;
import shop.onlineShop.domain.web.dto.MemberRequest;
import shop.onlineShop.domain.web.dto.MemberResponse;
import shop.onlineShop.global.uniformApi.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    //1. 회원 가입
    @PostMapping()
    public ApiResponse<Object> addMember(
            @RequestBody MemberRequest.MemberRequestDTO memberRequestDTO
            ){
        return ApiResponse.onSuccess(memberService.addMember(memberRequestDTO));
    }

    //2-1. 회원 단건 조회
    @GetMapping("/{memberId}")
    public ApiResponse<MemberResponse.MemberResponseDTO> getMember(
            @PathVariable Long memberId
    ){
        return ApiResponse.onSuccess(memberService.findOneMember(memberId));
    }

    //2-2. 회원 전체 조회
    @GetMapping()
    public ApiResponse<List<MemberResponse.MemberNameResponseDTO>> getAllMembers(){
        List<Member> findMembers = memberService.findAllMembers();
        return ApiResponse.onSuccess(MemberConverter.memberNameResponseDTOList(findMembers));
    }

    //3. 회원 수정
    @PutMapping("/{memberId}")
    public ApiResponse<MemberResponse.MemberResponseDTO> updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequest.MemberRequestDTO memberRequestDTO
    ){
        return ApiResponse.onSuccess(memberService.updateMember(memberId, memberRequestDTO));
    }

    //4. 회원 삭제
    @DeleteMapping("/{memberId}")
    public ApiResponse<Object> deleteMember(
            @PathVariable Long memberId
    ){
        memberService.deleteMember(memberId);
        return ApiResponse.onSuccess(null);
    }
}