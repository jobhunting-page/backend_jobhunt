package com.example.jobhuntwithjpa.Service;


import com.example.jobhuntwithjpa.Entity.Carrer_Board;
import com.example.jobhuntwithjpa.Entity.User;
import com.example.jobhuntwithjpa.dto.BoardReqDto;
import com.example.jobhuntwithjpa.dto.BoardResDto;
import com.example.jobhuntwithjpa.exception.CustomVaildationApiException;
import com.example.jobhuntwithjpa.repository.Carrer_BoardRepository;
import com.example.jobhuntwithjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final Carrer_BoardRepository Carrer_BoardRepository;

    private final UserService userService;

    private final UserRepository userRepository;



    @Transactional
    public long write(BoardReqDto boardReqDto) {

        Optional<User> user = Optional.ofNullable(userService.getMyUserWithAuthorities().orElseThrow(() -> {
            return new CustomVaildationApiException("로그인 후 이용해주세요");
        }));

        Long userId = user.get().getUserId();

        User myuser = userRepository.findById(userId).orElseThrow(() -> {
            return new CustomVaildationApiException("찾을 수 없는 Id입니다");
        });

        Carrer_Board board=new Carrer_Board();
        board.setUser(myuser);
        board.setSubject(boardReqDto.getSubject());
        board.setTitle(boardReqDto.getTitle());
        board.setWriter(boardReqDto.getWriter());
        board.setPreview(boardReqDto.getPreview());
        board.setContent(boardReqDto.getContent());
        Carrer_Board save = Carrer_BoardRepository.save(board);

        return save.getId();

    }

    @Transactional(readOnly = true)
    public List<BoardResDto> getboardlist(Pageable pageable){
        Page<Carrer_Board> Pagingboard = Carrer_BoardRepository.findAll(pageable);

        List<BoardResDto> pagingboard = Pagingboard.stream().map(board -> new BoardResDto(board))
                .collect(Collectors.toList());

        /**
         * 원래는 batch fecth size 를 써서 페이징쿼리랑 일반유저쿼리랑 한방에 묶어서 해야하지만 시간관계상 그냥 ... 가라로 하자
         */

        Optional<User> listuser = Optional.ofNullable(userService.getMyUserWithAuthorities().orElseThrow(() -> {
            return new CustomVaildationApiException("로그인 후 이용해주세요");
        }));

        Long userId = listuser.get().getUserId();

        User myuser = userRepository.findById(userId).orElseThrow(() -> {
            return new CustomVaildationApiException("찾을 수 없는 Id입니다");
        });

        BoardResDto boardResDto=new BoardResDto();
        boardResDto.setUser(myuser);

        return pagingboard;
    }

    @Transactional
    public Carrer_Board update(BoardReqDto boardReqDto) {

        long board_id = boardReqDto.getBoard_id();

        Carrer_Board board = Carrer_BoardRepository.findById(board_id).orElseThrow(() -> {
            return new CustomVaildationApiException("게시판 번호를 찾을 수 없습니다.");
        });
        board.setSubject(boardReqDto.getSubject());
        board.setTitle(boardReqDto.getTitle());
        board.setWriter(boardReqDto.getWriter());
        board.setContent(boardReqDto.getContent());
        board.setPreview(boardReqDto.getPreview());

        Carrer_Board savedboard = Carrer_BoardRepository.save(board);

        return savedboard;


    }

    @Transactional
    public void delete(long board_id) {

        Carrer_BoardRepository.deleteById(board_id);
    }
}
