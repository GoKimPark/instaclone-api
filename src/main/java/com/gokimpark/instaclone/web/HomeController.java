package com.gokimpark.instaclone.web;

import com.gokimpark.instaclone.web.member.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping
    public String homeLogin(@SessionAttribute(name = "login_member", required = false) LoginDto loginMember, Model model){

        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "member/memberProfile";
    }
}
