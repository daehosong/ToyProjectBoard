package oneoneone.oneboard.repository;

import oneoneone.oneboard.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //  DB와 작업

    //  이메일로 회원 정보 조회
    //  Optional = null 방지
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    Optional<MemberEntity> findById(Long id);
}
