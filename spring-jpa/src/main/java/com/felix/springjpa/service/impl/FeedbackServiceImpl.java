package com.felix.springjpa.service.impl;

import com.felix.springjpa.dao.FeedbackDao;
import com.felix.springjpa.model.Feedback;
import com.felix.springjpa.query.FeedbackQuery;
import com.felix.springjpa.service.FeedbackService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback, Serializable> implements FeedbackService {


    FeedbackDao feedbackDao;

    public FeedbackServiceImpl(FeedbackDao dao) {
        super(dao);
        feedbackDao = dao;
    }

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * 封装复杂查询
     * @param query
     * @return
     */
    public Page<Feedback> nativeComple(FeedbackQuery query){
        StringBuilder dataSql = new StringBuilder("SELECT  *  FROM (  SELECT f.id AS id, f.content AS content, f.createAt AS createAt, f.media AS media,"
                +" f.mobile AS mobile,f. STATUS AS STATUS,f.title AS title,f.type AS type, f.updateAt AS updateAt,f.userId AS userId FROM"
                +" feedback f LEFT JOIN feedbackreview reviews_ ON f.id = reviews_.feedbackId ");

        StringBuilder countSql=new StringBuilder("select count(f.id) FROM feedback f  LEFT JOIN feedbackreview reviews_ ON f.id = reviews_.feedbackId ");
        StringBuilder whereSql = new StringBuilder();
        if(query.getStatus() != null) {
            whereSql.append(" WHERE f. STATUS = :status");
        }else{
            query.setStatus(Feedback.STATUS_DELETED);
            whereSql.append(" WHERE f. STATUS != :status");
        }
        if (query.isMy()) {
            whereSql.append(" AND ( reviews_.role = :role  AND cast(reviews_.userId AS signed) = :userId )");
        }
        if(query.getType() != null){
            whereSql.append(" AND f.type = :type");
        }
        whereSql.append(" GROUP BY f.id") ;
        dataSql.append(whereSql).append(" ORDER BY  f.id DESC ) result");
        countSql.append(whereSql);
        Query dataQuery = entityManager.createNativeQuery(dataSql.toString(), Feedback.class);
        Query countQuery = entityManager.createNativeQuery(countSql.toString());
        dataQuery.setParameter("status",query.getStatus());
        countQuery.setParameter("status", query.getStatus());
        if(query.isMy()){
            dataQuery.setParameter("role",Feedback.ROLE_ADMIN);
            countQuery.setParameter("role", Feedback.ROLE_ADMIN);
            dataQuery.setParameter("userId", query.getAdminId());
            countQuery.setParameter("userId",  query.getAdminId());
        }

        if(query.getType() != null){
            dataQuery.setParameter("type",query.getType());
            countQuery.setParameter("type", query.getType());
        }
        Pageable pageable = PageRequest.of(query.getPageNum(), query.getPageSize());
        dataQuery.setFirstResult((int)pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());
        Long total = (long)(countQuery.getResultList().size());
        List<Feedback> content = total > pageable.getOffset() ? dataQuery.getResultList() : Collections.<Feedback> emptyList();
        Page<Feedback> feedbacks = new PageImpl<>(content, pageable, total);
        return  feedbacks;

    }

    @Override
    public Page<Feedback> getPage(FeedbackQuery query) {
        PageRequest pageable = PageRequest.of(query.getPageNum(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        if(query.isMy()){
            if(query.getStatus() != null){
                if(query.getType() != null) {
                    return feedbackDao.findAllByStatusAndTypeAndUserId(query.getStatus(), query.getType(), query.getAdminId(), pageable);
                } else {
                    return feedbackDao.findAllByStatusAndUserId(query.getStatus(), query.getAdminId(), pageable);
                }
            } else {
                if(query.getType() != null) {
                    return feedbackDao.findAllByStatusNotAndTypeAndUserId(Feedback.STATUS_DELETED, query.getType(), query.getAdminId(), pageable);
                } else{
                    return feedbackDao.findAllByStatusNotAndUserId(Feedback.STATUS_DELETED, query.getAdminId(), pageable);
                }
            }
        }
        Page<Feedback> all = feedbackDao.findAll((root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.getStatus() != null) {
                predicates.add(cb.equal(root.get("status").as(Integer.class), query.getStatus()));
            } else {
                predicates.add(cb.notEqual(root.get("status").as(Integer.class), Feedback.STATUS_DELETED));
            }
           /* if (query.isMy()) {
                predicates.add(cb.and(cb.equal(root.get("userId").as(Long.class), query.getAdminId())));
            }*/
            if (query.getType() != null) {
                predicates.add(cb.equal(root.get("type").as(Integer.class), query.getType()));
            }
            return cq.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageable);
    return   all;
    }

    @Override
    public Page<Feedback> getQuery(FeedbackQuery query) {
        return feedbackDao.getQuery(query);
    }

}
