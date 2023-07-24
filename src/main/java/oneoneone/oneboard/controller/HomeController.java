package oneoneone.oneboard.controller;

import lombok.RequiredArgsConstructor;
import oneoneone.oneboard.dto.MemberDTO;
import oneoneone.oneboard.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    //  서비스 클래스 객체 주입

    @GetMapping("/")
    public String index(){
        return "index";     // 반환주소 templates/index.html 로 페이지 호출
    }

}
