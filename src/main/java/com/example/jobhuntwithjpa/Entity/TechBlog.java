package com.example.jobhuntwithjpa.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor //모든 매개변수가 있는 생성자
@Builder
@Setter // 실무에선 setter 를 열어둬서는 안된다.
@Getter
@Table(name = "users")
@Entity
public class TechBlog {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "logo", length = 255)
    private String logo;

    @Column(name = "preview", length = 255)
    private String preview;

    @Column(name = "date", length = 255)
    private String date;
}
