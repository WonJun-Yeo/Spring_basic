package hello.core.member;

import java.util.IdentityHashMap;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
