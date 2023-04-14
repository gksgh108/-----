package com.tukorea.board.controller;

import com.tukorea.board.domain.Board;
import com.tukorea.board.dto.BoardForm;
import com.tukorea.board.dto.BoardModifyForm;
import com.tukorea.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class BoardController {


    private final BoardService service;

    @Autowired
    public BoardController(BoardService service){
        this.service = service;
    }
    /**
     * 페이지 연결
     * 김한호
     */
    @GetMapping("/board/form")
    public String addBoardFrom() {
        return "board/addBoardForm";
    }

    /**
     * 게시판 등록 Controller method
     */
    @PostMapping("/board/new")
    public String addBoard(BoardForm boardForm) {
        // 게시판 등록 메서드 호출
        service.addBoard(boardForm);
        // TODO 목록 개발 전까지는 /board/form으로 redirect 가게 처리
        return "redirect:/board/list";
    }

    /**
     * 게시판 목록 페이지 연결 Controller
     */
    @GetMapping("/board/list")
    public String getBoardList(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) {
        // 게시판 목록 조회 메서드 호출
        Map<String, Object> result = service.getBoardList(pageNum);

        model.addAttribute("pageNum", pageNum);

        // Service 결과 모두 model에 설정
        model.addAllAttributes(result);

        return "board/boardList";
    }

    /**
     * 게시판 상세 페이지 연결 Controller
     */
    @GetMapping("board/detail")
    public String getBoardDetail(@RequestParam int boardSeq, Model model) {
        // 게시판 상세정보 조회 메서드 호출
        Board board = service.getBoardDetail(boardSeq);
        model.addAttribute("board", board);

        return "board/boardDetail";
    }

    @ResponseBody
    @PostMapping("/board/checkPassword")
    public boolean checkPassword(@RequestParam int boardSeq, @RequestParam String password, Model model) {
        // 게시판 등록 메서드 호출
        boolean result = service.checkBoardOwner(boardSeq, password);

        return result;
    }

    /**
     * 게시물 정보 삭제 Controller Method
     */
    @PostMapping("/board/delete")
    public String deleteBoard(@RequestParam int boardSeq) {
        // 게시판 등록 메서드 호출
        service.deleteBoard(boardSeq);

        return "redirect:/board/list";
    }

    @PostMapping("/board/modifyForm")
    public String getBoardModifyForm(@RequestParam int boardSeq, Model model) {
        // 게시판 상세정보 조회 메서드 호출
        Board board = service.getBoardDetailForModify(boardSeq);
        model.addAttribute("board", board);

        return "board/modifyBoardForm";
    }

    /**
     * 게시물 정보 수정 Controller Method
     */
    @PostMapping("/board/modify")
    public String modifyBoard(BoardModifyForm boardForm) {
        // 게시판 등록 메서드 호출
        service.updateBoard(boardForm);

        return "redirect:/board/list";
    }
}