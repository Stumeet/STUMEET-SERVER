package com.stumeet.server.batch.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stumeet.server.batch.domain.study.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a "
        + "FROM Activity a "
        + "JOIN FETCH Study s ON s.id = a.study.id "
        + "JOIN ActivityParticipant ap ON a.id = ap.activity.id "
        + "WHERE ap.member.id = :memberId "
        + "AND (a.category = 'MEET' AND FUNCTION('DATE', a.startDate) = :today) "
        + "OR (a.category = 'ASSIGNMENT' AND FUNCTION('DATE', a.endDate) = :today) "
        + "ORDER BY CASE WHEN a.category = 'MEET' THEN a.startDate ELSE a.endDate END")
    List<Activity> findAllMemberTodayActivities(Long memberId, LocalDate today);
}
