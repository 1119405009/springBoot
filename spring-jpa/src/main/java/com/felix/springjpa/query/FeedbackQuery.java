package com.felix.springjpa.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedbackQuery {
    private Integer status;

    private int pageNum ;
    private int pageSize;

    private Integer type;
    private boolean my;
    @JsonIgnore
    private Long adminId;



}

