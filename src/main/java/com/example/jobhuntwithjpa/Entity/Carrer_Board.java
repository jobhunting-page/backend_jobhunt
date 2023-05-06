package com.example.jobhuntwithjpa.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Getter
@Setter
@DynamicInsert //@DynamicInsert사용 default 값 설정 시 사용
@EntityListeners(AuditingEntityListener.class) //Auditing(자동으로 값 매핑)기능 추가
public class Carrer_Board{


    @Column(name = "board_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //제목
    private String title;

    //과목
    private String subject;

    // 작성자(멘토이름)
    private String writer;

    private String content;

    private String preview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdat;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedat;



}