package com.seul.jpa.study.datajpa.repository;

import com.seul.jpa.study.datajpa.entity.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
// 네이밍 규칙 (interface name) + Impl
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }
}
