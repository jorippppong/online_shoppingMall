package shop.onlineShop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.onlineShop.domain.entity.Member;
import shop.onlineShop.domain.repository.MemberRepository;
import shop.onlineShop.domain.web.converter.MemberConverter;
import shop.onlineShop.domain.web.dto.MemberRequest;
import shop.onlineShop.domain.web.dto.MemberResponse;
import shop.onlineShop.global.exception.CustomException;
import shop.onlineShop.global.uniformApi.ErrorStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static shop.onlineShop.global.uniformApi.ErrorStatus.MEMBER_NOT_FOUND;
import static shop.onlineShop.global.uniformApi.ErrorStatus.NICKNAME_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    //1. 회원 가입
    @Transactional
    public MemberResponse.MemberIdDTO addMember(MemberRequest.MemberRequestDTO memberRequestDTO) {
        validateDuplicateMember(memberRequestDTO);
        Member newMember = MemberConverter.memberRequestConverter(memberRequestDTO);
        memberRepository.save(newMember);
        return MemberConverter.idResponseConverter(newMember.getId());
    }

    //중복 회원 검증
    private void validateDuplicateMember(MemberRequest.MemberRequestDTO memberRequestDTO){
        Member findMember = memberRepository.findByName(memberRequestDTO.getName());
        if(!Objects.isNull(findMember)){
            throw new CustomException(ErrorStatus.MEMBER_ALREADY_EXIST);
        }
    }

    //2. 회원 조회
    //2-1. 회원 단건 조회
    @Transactional(readOnly = true)
    public MemberResponse.MemberResponseDTO findOneMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            return MemberConverter.memberResponseConverter(member);
        }else{
            throw new CustomException(MEMBER_NOT_FOUND);
        }
    }

    //2-2. 회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findAllMembers(){
        return memberRepository.findAll();
    }

    //3. 회원 수정
    @Transactional
    public MemberResponse.MemberResponseDTO updateMember(Long memberId, MemberRequest.MemberRequestDTO memberRequestDTO) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            //이름 null 일때
            if(memberRequestDTO.getName().isBlank()){
                throw new CustomException(NICKNAME_NOT_EXIST);
            }

            member.setName(memberRequestDTO.getName());
            member.setAddress(MemberConverter.addressRequestConverter(memberRequestDTO, member.getAddress()));
            memberRepository.save(member);

            return MemberConverter.memberResponseConverter(member);
        }else{
            throw new CustomException(MEMBER_NOT_FOUND);
        }
    }

    //4. 회원 삭제
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
