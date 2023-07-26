package oneoneone.oneboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    //  서비스 클래스 객체 주입

    @GetMapping("/")
    public String index(){
        return "index";     // 반환주소 templates/index.html 로 페이지 호출
    }

}
