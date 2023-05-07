package com.example.jobhuntwithjpa.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class BoardReqDto {


    private long board_id;

    private String title;

    private String subject;

    private String writer;

    private String preview;

    private String content;


}
