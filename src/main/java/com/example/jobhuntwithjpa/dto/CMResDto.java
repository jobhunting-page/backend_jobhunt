package com.example.jobhuntwithjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMResDto<T>{

    //이건 공통 dto 를 만들어줬어

    private int code; // 1(성공),-1(실패)
    private String message;
    private T data;
}
