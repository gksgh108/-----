package com.tukorea.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession();

        //session 강제 설정
        session.setAttribute("sMemberSeq", 1);
        session.setAttribute("sMemberId", "test_user");

        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        //session 삭제
        if (session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
