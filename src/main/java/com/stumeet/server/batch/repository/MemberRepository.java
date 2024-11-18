package com.stumeet.server.batch.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stumeet.server.batch.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT DISTINCT m "
        + "FROM Member m "
        + "JOIN ActivityParticipant ap ON m.id = ap.member.id "
        + "JOIN Activity a ON a.id = ap.activity.id "
        + "WHERE (a.category = 'MEET' AND FUNCTION('DATE', a.startDate) = :today) "
        + "OR (a.category = 'ASSIGNMENT' AND FUNCTION('DATE', a.endDate) = :today) "
        + "ORDER BY m.id")
    Slice<Member> findTodayActivityParticipants(LocalDate today, Pageable pageable);
}
