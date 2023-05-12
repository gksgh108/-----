package com.tukorea.board.service;

import com.tukorea.board.dao.BoardMapper;
import com.tukorea.board.domain.Board;
import com.tukorea.board.dto.BoardForm;
import com.tukorea.board.dto.BoardList;
import com.tukorea.board.dto.BoardModifyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
    public class BoardService {
        private final BoardMapper mapper;
        @Autowired
        public BoardService(BoardMapper mapper) {
            this.mapper = mapper;
        }

        /**
         * 게시판 등록 Service Method
         */
        public void addBoard(BoardForm boardForm) {
        try {
            // 등록용 파라미터 정제(DTO -> Domain)
            Board board = new Board();
            board.setTitle(boardForm.getTitle());
            board.setWriter(boardForm.getWriter());
            board.setPassword(boardForm.getPassword());
            board.setContents(boardForm.getContent());
            // 게시판 등록
            int resultCount = mapper.insertBoard(board);
            System.out.println("게시판 등록 완료(건수 : " + resultCount + "건)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        /**
         * 게시판 목록 조회
         */
        public Map<String, Object> getBoardList(int pageNum) {
            Map<String, Object> result = new HashMap<String, Object>();

            try{
                // [1]게시물 총 건수 조회
                int totalCount = mapper.selectBoardListTotalCount();
                result.put("totalCount", totalCount);

                System.out.println("[1] 게시물 총 건수 조회 완료(" + totalCount + "건)");

                // [2]게시판 목록 조회
                // 목록 조회용 파라미터 설저
                int listNum = 10; // 게시판 페이지 별 건수 설정
                int startNum = (pageNum -1) * listNum; // 시작점 : 1페이지 (1-1) * 가져올 갯수 = 0부터 시작

                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("startNum", startNum);
                paramMap.put("listNum", listNum);

                // 게시판 목록 조회
                List<Board> dbBoardList = mapper.selectBoardList(paramMap);

                // 도메인에서 게시판 목록용 DTO로 전환
                List<BoardList> boardList = new ArrayList<BoardList>();
                int listStartNum = ((pageNum -1) * listNum) + 1; // 1페이지 1번째 게시물 ((1-1) * 10) + 1

                for (Board board : dbBoardList) {
                    BoardList listObj = new BoardList();

                    listObj.setNo(listStartNum++); //NO
                    listObj.setBoardSeq(board.getBoardSeq()); // 게시물 시퀀스
                    listObj.setTitle(board.getTitle()); // 제목
                    listObj.setWriter(board.getWriter()); // 작성자
                    listObj.setHits(board.getHits()); // 조회수
                    listObj.setRegDt(board.getRegDt()); // 등록일시

                    boardList.add(listObj);
                }

                result.put("boardList", boardList);

                System.out.println("[2] 게시물 목록 조회 완료");
                // [3] 게시판 페이징 생성
                // [3-1] 페이징 계산용 변수 설정
                int pageUnitNum = 5; // 밑에 보이는 페이지 수

                // [3-2] 총 페이지 계산
                int totalPagingMum = (totalCount / listNum) + (totalCount % listNum == 0 ? 0 : 1); // 전체 페이지 수 정확히 나누어 떨어지면 그대로 아니면 1 추가

                // [3-3] 배열 값 비교하여 페이징 시작 번호 return
                int totalPagingUnitNum = (totalPagingMum / pageUnitNum) + (totalPagingMum % pageUnitNum == 0 ? 0 : 1); // 페이지 단위 수 계산
                for (int i=0; i<totalPagingUnitNum; i++) {
                    // 단위 별 시작 페이지 번호와 종료 페이지 번호를 구한 뒤 비교하여 포함되는 페이징 그룹 return
                    int startUnitNum = (i * pageUnitNum) + 1;
                    int endUnitNum = (i + 1) * pageUnitNum;

                    // 페이징 단위 종료 번호가 총 페이징 번호보다 클 경우 총 페이징 번호가 마지막이 됨
                    if (endUnitNum > totalPagingMum) {
                        endUnitNum = totalPagingMum;
                    }
                    if (pageNum >= startUnitNum && pageNum <= endUnitNum) {
                        result.put("startUnitNum", startUnitNum);
                        result.put("endUnitNum", endUnitNum);
                        result.put("totalPagingNum", totalPagingMum);

                        break;
                    }
                }

                System.out.println("[3] 게시판 페이지 설정 완료");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

    /**
     * 게시판 상세 정보 조회
     */
    public Board getBoardDetail(int boardSeq) {
        Board board = null;
        try {
            // [1] 게시판 상세 정보 조회
            board = mapper.selectBoardInfo(boardSeq);

            System.out.println("[1] 게시판 상세 조회 완료");

            // [2] 해당 게시물 조회수 1 증가
            mapper.updateBoardHits(boardSeq);

            System.out.println("[2] 게시물 조회수 증가 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    /**
     * 게시물 소유 확인 Service Method
     */
    public boolean checkBoardOwner(int boardSeq, String password) {
        boolean result = false;

        try {
            // 게시물 패스워드 일치 여부 확인
            Map<String, Object> paramMap = new HashMap<String,Object>();
            paramMap.put("boardSeq", boardSeq);
            paramMap.put("password", password);

            // 일치하는 게시물 있는지 확인
            int totalCount = mapper.selectBoardPasswordForCheck(paramMap);
            System.out.println("게시물 총 건수 조회 완료(" + totalCount + "건)");

            if (totalCount > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 게시물 정보 삭제 Service Method
     */
    public void deleteBoard(int boardSeq) {
        try {
            // 게시물 삭제
            mapper.deleteBoard(boardSeq);

            System.out.println("게시물 삭제 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 수정폼 페이지 출력용
     */
    public Board getBoardDetailForModify(int boardSeq) {
        // 전역변수
        Board board = null;

        try {
            // 게시판 상세 정보 조히
            board = mapper.selectBoardInfo(boardSeq);

            System.out.println("게시판 상세 조회 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }
    /**
     * 게시물 정보 수정 Service Method
     */
    public void updateBoard(BoardModifyForm boardForm) {
        try{
            // 수정용 파라미터 정제(DTO -> Domain)
            Board board = new Board();
            board.setBoardSeq(boardForm.getBoardSeq());
            board.setTitle(boardForm.getTitle());
            board.setWriter(boardForm.getWriter());
            board.setContents(boardForm.getContents());

            // 게시판 수정
            int resultCount = mapper.updateBoard(board);
            System.out.println("게시판 수정 완료 (건수 : " + resultCount + "건");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
