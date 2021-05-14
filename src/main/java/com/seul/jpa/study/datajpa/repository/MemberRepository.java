package com.seul.jpa.study.datajpa.repository;

import com.seul.jpa.study.datajpa.dto.MemberDto;
import com.seul.jpa.study.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query(name = "Member.findByUsername") // @Query 가 없어도 Spring jpa 에서 엔티티명.메서드 로 네임드 쿼리를 찾기 때문에 해당 내용을 제거해도 무방하다
    List<Member> findByUsername(@Param("username") String username);

    @Query("SELECT m FROM Member m WHERE m.username = :username and m.age = :age") // 이름 없는 네임드 쿼리라고 볼 수 있다.
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("SELECT new com.seul.jpa.study.datajpa.dto.MemberDto(m.id, m.username, t.name) FROM Member m JOIN m.team t")
    List<MemberDto> findMemberDto();

    @Query("SELECT m FROM Member m WHERE m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);
    Optional<Member> findOptionalByUsername(String username);

    @Query(value = "SELECT m FROM Member m LEFT JOIN m.team t",
            countQuery = "SELECT COUNT(m.username) FROM Member m") // count query 까지 join 이 들어갈 필요가 없을때는 count query 분리가 가능하다.
    Page<Member> findByAge(int age, Pageable pageable);

//    Slice<Member> findByAge(int age, Pageable pageable); // count 쿼리 없이 다음 페이지만 확인 가능(내부적으로 limit + 1 조회)

    @Modifying(clearAutomatically = true) // 쿼리 후 영속성 컨텍스트를 자동으로 clear 해준다.
    @Query("UPDATE Member m set m.age = m.age + 1 WHERE m.age >= :age")
    int bulkAgePlus(@Param("age") int age);
}
