package spring.studyspring.repository;

import org.springframework.stereotype.Repository;
import spring.studyspring.domain.Member;

import java.util.*;

//아직 DB가 정해지지 않음
public class MemoryMemberRepository implements MemberRepository{
    //실무에선 동시성 문제 때문에 ConcurrentHashMap 사용
    private static Map<Long,Member> store = new HashMap<>();
    //마찬가지로 동시성 문제를 고려해서 atomicLong 사용
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
