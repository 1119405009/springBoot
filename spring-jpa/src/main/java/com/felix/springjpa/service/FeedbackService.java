package com.felix.springjpa.service;

import com.felix.springjpa.model.Feedback;
import com.felix.springjpa.query.FeedbackQuery;
import org.springframework.data.domain.Page;

import java.io.Serializable;


public interface FeedbackService extends BaseService<Feedback, Serializable>{

    public Page<Feedback> nativeComple(FeedbackQuery query);

    public  Page<Feedback> getPage(FeedbackQuery query);

    Page<Feedback> getQuery(FeedbackQuery query);
}
