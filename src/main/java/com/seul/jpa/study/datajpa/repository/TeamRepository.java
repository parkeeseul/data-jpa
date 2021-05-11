package com.seul.jpa.study.datajpa.repository;

import com.seul.jpa.study.datajpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
