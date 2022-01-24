package spring.studyspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring.studyspring.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트가 하나 끝날때마다 실행
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("hojoon");

        repository.save(member);

        //바로 get으로 꺼내는 방식은 좋은 방식이 아니다.
        Member result = repository.findById(member.getId()).get();
        //올바르게 저장이됐다면 member와 result가 같아야된다.
        //junit assertions
        //Assertions.assertEquals(member,result);

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("hojoon");
        repository.save(member1);

        Member member2 =new Member();
        member2.setName("hojoon2");
        repository.save(member2);

        Member result=repository.findByName("hojoon").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("hojoon");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("hojoon2");
        repository.save(member2);

        Member member3=new Member();
        member3.setName("hojoon3");
        repository.save(member3);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(3);
    }
}
