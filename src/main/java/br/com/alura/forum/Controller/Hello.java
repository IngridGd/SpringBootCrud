package br.com.alura.forum.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {
    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "hello bitch";
    }
}