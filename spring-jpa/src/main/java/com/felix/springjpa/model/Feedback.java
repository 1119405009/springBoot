package com.felix.springjpa.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 反馈实体类
 * */
@Entity
@Table(name = "feedback")
@Getter
@Setter
public class Feedback implements Serializable {

  public static final int TYPE_REFUND = 1;
  public static final int TYPE_DEVICE = 2;
  public static final int TYPE_WECHAT = 3;
  public static final int TYPE_STATION = 4;
  public static final int TYPE_OTHER = 5;
  public static final int STATUS_INIT = 1;
  public static final int STATUS_HAS_REPLY = 2;
  public static final int STATUS_READED = 3;
  public static final int STATUS_COMPLETED = 4;
  public static final int STATUS_DELETED = 5;
  public static final int ROLE_USER = 0;
  public static final int ROLE_ADMIN = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //标题
  private String title;
  //内容
  private String content;
  //图片地址
  private String media;
  //类型状态码
  private Integer type;
  //进行状态码
  private Integer status = STATUS_INIT;
  //用户Id
  private Long userId;
  //创建时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date createAt = new Date();
  //更新时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date updateAt = new Date();
  //手机号码
  private String mobile;

  @Transient
  private List<FeedbackReview> reviews;

  public boolean isNotCompleted() {
    return status.intValue() != STATUS_COMPLETED;
  }
}
