package spring.studyspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.studyspring.domain.Member;
import spring.studyspring.service.MemberService;

import java.util.List;

/*
스프링 빈을 등록하는 2가지 방법:
    - 컴포넌트 스캔과 자동 의존관계 설정
    - 자바코드로 직접 스프링빈 등록하기
기본적으로 싱글톤으로 등록하기 때문에 같은 스프링빈이면 같은 인스턴스이다
 */
@Controller
public class MemberController {
    private final MemberService memberService;

    // 스프링 컨테이너가 생성될 때 자동적으로 호출에 서비스를 연결해준다.
    //DI :Defendency infection
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
