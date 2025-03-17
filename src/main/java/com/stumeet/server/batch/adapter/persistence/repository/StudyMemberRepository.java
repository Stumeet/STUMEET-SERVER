package com.stumeet.server.batch.adapter.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stumeet.server.batch.domain.study.StudyMember;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {

    @Query("SELECT sm FROM StudyMember sm "
            + "JOIN sm.study s "
            + "WHERE s.isFinished = false "
            + "AND FUNCTION('DAYNAME', s.startDate) = :today")
    Slice<StudyMember> findStudyMembersByStudyStartDay(@Param("today") String today, Pageable pageable);
}
