package oneoneone.oneboard.service;

import lombok.RequiredArgsConstructor;
import oneoneone.oneboard.dto.MemberDTO;
import oneoneone.oneboard.entity.MemberEntity;
import oneoneone.oneboard.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        //  1. dto -> entity 변환
        //  2. repository
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //  Repository의 save 메서드 호출 ( 조건. entity객체를 넘겨줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO) {
        //  1. 회원이 입력한 이메일로 DB에서 조회
        //  2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        if (byMemberEmail.isPresent()) {
            //  1. 조회 결과가 있을 시 (이메일을 가진 회원이 존재)
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치
                //  entity-> dto 변환 후 리턴
                MemberDTO dto = memberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                //  비밀번호 불일치
                return null;
            }
        } else {
            //  2. 조회 결과가 없다. (이메일을 가진 회원이 미존재)
            return null;
        }
    }


    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
/*            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;*/
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            //  1. 조회 결과가 있을 시 (이메일을 가진 회원이 존재) -> 사용 불가능
            return null;
        }
        else {
            //  2. 조회 결과가 없을 시 (이메일을 가진 회원이 미존재) -> 사용 가능
            return "ok";
        }

    }
}