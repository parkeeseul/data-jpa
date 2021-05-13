package com.seul.jpa.study.datajpa.repository;

import com.seul.jpa.study.datajpa.dto.MemberDto;
import com.seul.jpa.study.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
}
