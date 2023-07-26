package oneoneone.oneboard.controller;

import lombok.RequiredArgsConstructor;
import oneoneone.oneboard.dto.BoardDTO;
import oneoneone.oneboard.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String boardMain(){
        return "/board/boardMain";
    }
    @GetMapping("/board/save")
    public String boardSaveForm(){
    return "/board/boardSave";
    }
    @PostMapping("/board/save")
    public String boardSave(@ModelAttribute BoardDTO boardDTO){
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "/board/boardMain";
    }

    @GetMapping("/board/list")
    public String boardListForm(Model model){
        //  DB에서 전체 게시글 데이터를 가져와서 list.html에 뿌려줌.
        System.out.println("Model:"+model);
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "/board/boardList";
    }
/*    @PostMapping("/board/list")
    public String boardList(@ModelAttribute BoardDTO boardDTO){
        System.out.println("boardDTO = " + boardDTO);

        return "/board/boardList";
    }*/
}
