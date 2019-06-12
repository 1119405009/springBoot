package com.felix.springjpa.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "feedbackReview")
@Getter
@Setter
public class FeedbackReview implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "feedbackId")
  private Long feedbackId;
  @Column(name = "userId")
  private Long userId;
  private String content;
  private String media;
  private Integer role;
  private String name;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date createAt = new Date();

  public boolean isAdminReview() {
    return role.intValue() == Feedback.ROLE_ADMIN;
  }
}
