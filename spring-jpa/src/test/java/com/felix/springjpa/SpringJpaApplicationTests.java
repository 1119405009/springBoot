package com.felix.springjpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.springjpa.model.Feedback;
import com.felix.springjpa.query.FeedbackQuery;
import com.felix.springjpa.service.FeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJpaApplicationTests {


    @Autowired
    FeedbackService feedbackService;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 原生
     */
    @Test
    public  void nativeComple(){
        FeedbackQuery query=new FeedbackQuery();
        query.setMy(true);
        query.setPageNum(0);
        query.setPageSize(10);
        query.setStatus(3);
        query.setAdminId((long)2);
        Page<Feedback> feedbacks = feedbackService.nativeComple(query);
        System.out.println(feedbacks.getContent().size());
    }

    /**
     * hql
     */
    @Test
    public  void getPage(){
        FeedbackQuery query=new FeedbackQuery();
        query.setMy(true);
        query.setPageNum(0);
        query.setPageSize(10);
        query.setStatus(3);
        query.setAdminId((long)2);
        Page<Feedback> feedbacks = feedbackService.getPage(query);
        System.out.println(feedbacks.getContent().size());
    }

    /**
     * 原生
     */
    @Test
    public void getQuery(){
        FeedbackQuery query=new FeedbackQuery();
        query.setMy(true);
        query.setPageNum(0);
        query.setPageSize(10);
        query.setStatus(3);
        query.setAdminId((long)2);
        Page<Feedback> feedbacks = feedbackService.getQuery(query);
    }

    @Test
    public void contextLoads() {
    }

}
