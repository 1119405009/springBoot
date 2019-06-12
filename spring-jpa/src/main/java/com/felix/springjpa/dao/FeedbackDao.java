package com.felix.springjpa.dao;

import com.felix.springjpa.dao.BaseDao;
import com.felix.springjpa.model.Feedback;
import com.felix.springjpa.query.FeedbackQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface FeedbackDao extends BaseDao<Feedback, Serializable> {


    @Query(value = "select f.* from feedback f left join feedbackReview r on r.feedbackId=f.id where f.status!=?1 and f.type=?2 and r.role=1 and r.userId=?3", nativeQuery = true)
    Page<Feedback> findAllByStatusNotAndTypeAndUserId(Integer statusDeleted, Integer type, Long adminId, Pageable pageable);

    @Query(value = "select f.* from feedback f left join feedbackReview r on r.feedbackId=f.id where f.status=?1 and f.type=?2 and r.role=1 and r.userId=?3", nativeQuery = true)
    Page<Feedback> findAllByStatusAndTypeAndUserId(Integer status, Integer type, Long adminId, Pageable pageable);

    @Query(value = "select f.* from feedback f left join feedbackReview r on r.feedbackId=f.id where f.status=?1 and r.role=1 and r.userId=?2", nativeQuery = true)
    Page<Feedback> findAllByStatusAndUserId(Integer status, Long adminId, Pageable pageable);

    @Query(value = "select f.* from feedback f left join feedbackReview r on r.feedbackId=f.id where f.status!=?1 and r.role=1 and r.userId=?2", nativeQuery = true)
    Page<Feedback> findAllByStatusNotAndUserId(Integer status, Long adminId, Pageable pageable);

    /**
     * 传入对象
     * @param query
     * @return
     */

    @Query(value ="SELECT  *  FROM ( SELECT f.id AS id, f.content AS content,f.createAt AS createAt,f.media AS media,f.mobile AS mobile,f. STATUS AS STATUS,f.title AS title,f.type AS type,f.updateAt AS updateAt,f.userId AS userId FROM feedback f "
            +  " LEFT JOIN feedbackreview reviews_ ON f.id = reviews_.feedbackId  WHERE   f. STATUS = #{#query.status}  and ( reviews_.role = 1  AND cast(reviews_.userId AS signed) = #{#query.adminId}) GROUP BY f.id  ORDER BY f.id desc ) result ",
            countQuery ="select count(f.id) FROM feedback f  LEFT JOIN feedbackreview reviews_ ON f.id = reviews_.feedbackId  WHERE f. STATUS = #{#query.status}  and ( reviews_.role = 1  AND cast(reviews_.userId AS signed) = #{#query.adminId}) GROUP BY f.id", nativeQuery = true)
    Page<Feedback> getQuery(FeedbackQuery query);
}

