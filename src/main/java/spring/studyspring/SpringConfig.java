package spring.studyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.studyspring.repository.MemberRepository;
import spring.studyspring.repository.MemoryMemberRepository;
import spring.studyspring.service.MemberService;

//직접 스프링 빈 등록하가
//필드, setter, 생성자 주입방식이 있다. 생성자 선호
//정형화되지 않거나 상황에 따라 구현클래스를 변경해야될 경우 직접 빈 등록한다.
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository() );
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
