package spring.studyspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.studyspring.domain.Member;

import java.util.Optional;

public interface SpringDataJpaRepository extends JpaRepository<Member,Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
