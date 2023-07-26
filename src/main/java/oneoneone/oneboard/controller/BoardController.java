package oneoneone.oneboard.controller;

import oneoneone.oneboard.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @GetMapping("/board")
    public String boardMain(){
        return "/board/boardMain";
    }
    @GetMapping("/board/save")
    public String boardSaveForm(){
    return "/board/boardSave";
    }
    /*@PostMapping("/board/save")
    public String boardSave(@ModelAttribute BoardDTO boardDTO){
    }*/
}
