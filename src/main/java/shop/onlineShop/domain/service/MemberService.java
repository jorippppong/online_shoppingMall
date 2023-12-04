package shop.onlineShop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.onlineShop.domain.entity.Member;
import shop.onlineShop.domain.repository.repository.MemberRepository;
import shop.onlineShop.domain.web.converter.MemberConverter;
import shop.onlineShop.domain.web.dto.MemberRequestDTO;
import shop.onlineShop.domain.web.dto.MemberResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    //1. 회원 가입
    @Transactional
    public void addMember(MemberRequestDTO memberRequestDTO) {
        validateDuplicateMember(memberRequestDTO);
        memberRepository.save(MemberConverter.memberRequestConverter(memberRequestDTO));
    }

    //중복 회원 검증
    private void validateDuplicateMember(MemberRequestDTO memberRequestDTO){
        List<Member> findMembers = memberRepository.findByName(memberRequestDTO.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //2. 회원 조회
    //2-1. 회원 단건 조회
    @Transactional(readOnly = true)
    public MemberResponseDTO findOneMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            return MemberConverter.memberResponseConverter(member);
        }else{
            throw new RuntimeException("cannot find ID");
        }
    }

    //2-2. 회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findAllMembers(){
        return memberRepository.findAll();
    }


    //3. 회원 수정
    @Transactional
    public MemberResponseDTO updateMember(Long memberId, MemberRequestDTO memberRequestDTO) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            member.setName(memberRequestDTO.getName());
            member.setAddress(MemberConverter.addressRequestConverter(memberRequestDTO));
            memberRepository.save(member);
            return MemberConverter.memberResponseConverter(member);
        }else{
            throw new RuntimeException("cannot find ID");
        }
    }

    //4. 회원 삭제
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
