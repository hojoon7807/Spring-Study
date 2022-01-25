 package spring.studyspring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.studyspring.domain.Member;
import spring.studyspring.repository.MemberRepository;
import spring.studyspring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    //테스트코드는 필드 주입방식으로 해도 괜찮다.
    @Autowired MemberService memberService;
    //서비스에서 생성된 레포지토리와 다른 객체
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hojoon");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member = new Member();
        member.setName("hojoon");
        Member member2 = new Member();
        member2.setName("hojoon");
        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        try{
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}