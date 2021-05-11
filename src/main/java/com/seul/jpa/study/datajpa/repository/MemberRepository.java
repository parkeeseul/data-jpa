package com.seul.jpa.study.datajpa.repository;

import com.seul.jpa.study.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
