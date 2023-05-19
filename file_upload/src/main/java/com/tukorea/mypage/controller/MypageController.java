package com.tukorea.mypage.controller;

import com.tukorea.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class MypageController {

    private MypageService service;

    @Autowired
    public MypageController(MypageService service){
        this.service = service;
    }

    @GetMapping("/mypage")
    public String mypage(Model model, HttpServletRequest request) {
        // get session
        HttpSession session = request.getSession();
        int memberSeq = (int) session.getAttribute("sMemberSeq");

        HashMap<String, Object> resultMap = service.getMemberDetail(memberSeq);
        model.addAttribute("member", resultMap);

        return "mypage/mypage";
    }

    @ResponseBody
    @PostMapping("/mypage/modifyProfileImage")
    public HashMap<String, Object> modifyProfileImage(
            @RequestPart(value ="profileImg", required = false) MultipartFile profileImg,
            HttpServletRequest request) {
        // get session
        HttpSession session = request.getSession();;
        int memberSeq = (int) session.getAttribute("sMemberSeq");

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("memberSeq", memberSeq);
        paramMap.put("file", profileImg);

        // TODO 프로필 이미지 수정 Service Method 호출
        HashMap<String,Object> resultMap = service.modifyProfileImage(paramMap);
        return resultMap;
    }
}
