package com.example.jobhuntwithjpa.dto;

import com.example.jobhuntwithjpa.Entity.Carrer_Board;
import com.example.jobhuntwithjpa.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardResDto {
    private long id;

    private String subject;
    private String title;

    private String preview;

    private String content;
    private User user;
    private String writer;

    private String created_at;

    private String updated_at;

    public BoardResDto(Carrer_Board board) {

        this.id=board.getId();
        this.subject=board.getSubject();
        this.title=board.getTitle();
        this.writer=board.getWriter();
        this.user=board.getUser();
        this.created_at= String.valueOf(board.getCreatedat());
        this.updated_at= String.valueOf(board.getUpdatedat());
        this.content=board.getContent();
        this.preview=board.getPreview();
    }


}
