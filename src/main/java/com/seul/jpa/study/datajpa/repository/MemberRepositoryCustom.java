package com.seul.jpa.study.datajpa.repository;

import com.seul.jpa.study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
