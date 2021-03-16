package com.ex.board.controller;


import com.ex.board.dto.BoardDto;
import com.ex.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor //클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성(Boardservice 객체 주입시)
public class BoardController {
    private BoardService boardService;

    //* 메인 페이지 _ 게시물 전체 리스트 확인 가능 *//
    @GetMapping("/")
    public String list(Model model){
        List<BoardDto> boardList = boardService.getBoardlist();

        model.addAttribute("boardList",boardList);
        return "list";
    }

    @GetMapping("/post")
    public String write(){
        return "write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);

        return "redirect:/";
    }

    //* 게시글 상세 조회 페이지 *//
    @GetMapping("/post/{no}") //post뒤에 오는 유동적인 path {변수}표시
    public String detail(@PathVariable("no") Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);
        //Model을 매개변수로 전달받아, key, value 쌍으로 전달
        model.addAttribute("boardDto",boardDto);
        return "detail";
    }

    //* 게시글 수정 페이지 *//
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no")Long no,Model model){
        BoardDto boardDto = boardService.getPost(no);

        model.addAttribute("boardDto",boardDto);
        return "update";
    }

    @PutMapping("/post/edit/{no}") //put : 동일한 요청을 한 번 보내는 것과 여러번 연속으로 보내는 것이 같은 효과를지닌다.
    public String update(BoardDto boardDto){
        //update도 동일하게 savepost 사용 jpaRepository 는 insert와 update동일하게 작동
        //동일한 아이디가 존재한다면 업데이트(수정) 해당 아이디가 없다면 추가
        boardService.savePost(boardDto);

        return "redirect:/";
    }

    //* 게시글 삭제  *//
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no){
        boardService.deletePost(no);

        return "redirect:/";
    }

    /* 게시글 검색 */
    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        //클라이언트에서 넘겨주는 keyword를 검색어로 활용
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList",boardDtoList);

        return "list";
    }
}