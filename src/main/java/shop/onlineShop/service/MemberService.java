package shop.onlineShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.onlineShop.domain.Member;
import shop.onlineShop.repository.MemberRepository;
import shop.onlineShop.web.converter.MemberConverter;
import shop.onlineShop.web.dto.MemberRequestDTO;
import shop.onlineShop.web.dto.MemberResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    public void addMember(MemberRequestDTO memberRequestDTO) {
        memberRepository.save(MemberConverter.memberRequestConverter(memberRequestDTO));
    }

    @Transactional
    public MemberResponseDTO getMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            return MemberConverter.memberResponseConverter(member);
        }else{
            throw new RuntimeException("cannot find ID");
        }
    }

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

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
