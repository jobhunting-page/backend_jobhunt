package com.example.jobhuntwithjpa.controller;


import com.example.jobhuntwithjpa.Entity.Carrer_Board;
import com.example.jobhuntwithjpa.Service.BoardService;
import com.example.jobhuntwithjpa.dto.BoardReqDto;
import com.example.jobhuntwithjpa.dto.BoardResDto;
import com.example.jobhuntwithjpa.dto.CMResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags={"게시글 API"})
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class boardController {


    private final BoardService boardService;


    @ApiOperation(value="게시글 쓰기")
    //로그인 api 경로
    @PostMapping("/write")
    public ResponseEntity<?> board_write(@Valid @RequestBody BoardReqDto boardReqDto) {


        long ans = boardService.write(boardReqDto);

        return new ResponseEntity<>(new CMResDto<>(1,"게시글 쓰기 성공!",ans), HttpStatus.OK);

    }

    @ApiOperation(value="게시글 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<?> board_list(@PageableDefault(size = 10, sort = "createdat",  direction = Sort.Direction.DESC) Pageable pageable){
        List<BoardResDto> getboardlist = boardService.getboardlist(pageable);
        return new ResponseEntity<>(new CMResDto<>(1,"게시글조회 성공!",getboardlist), HttpStatus.OK);
    }

    @ApiOperation(value="게시글 수정")
    @PutMapping("/update")
    public ResponseEntity<?> board_update(@RequestBody BoardReqDto boardReqDto){

        Carrer_Board updatedboard = boardService.update(boardReqDto);
        return new ResponseEntity<>(new CMResDto<>(1,"게시글수정 성공!",updatedboard), HttpStatus.OK);
    }

    @ApiOperation(value="게시글 삭제")
    @DeleteMapping("/delete/{board_id}")
    public ResponseEntity<?> board_delete(@PathVariable ("board_id") long board_id){

        boardService.delete(board_id);
        return new ResponseEntity<>(new CMResDto<>(1,"게시글삭제 성공!","success"), HttpStatus.OK);
    }

}
