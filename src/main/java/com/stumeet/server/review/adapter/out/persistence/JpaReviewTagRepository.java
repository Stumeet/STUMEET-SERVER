package com.stumeet.server.review.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stumeet.server.review.adapter.out.persistence.dto.ReviewTagCountDto;
import com.stumeet.server.review.adapter.out.persistence.entity.ReviewTagJpaEntity;

public interface JpaReviewTagRepository extends JpaRepository<ReviewTagJpaEntity, Long> {

    @Query("SELECT new com.stumeet.server.review.adapter.out.persistence.dto.ReviewTagCountDto(rt.reviewTag, COUNT(rt)) "
        + "FROM ReviewTagJpaEntity rt "
        + "JOIN rt.review r "
        + "WHERE r.revieweeId = :memberId "
        + "GROUP BY rt.reviewTag "
        + "ORDER BY COUNT(rt) DESC, rt.reviewTag ASC ")
    List<ReviewTagCountDto> countReviewTagByRevieweeIdOrdered(Long memberId);
}
