package oneoneone.oneboard.controller;

import lombok.RequiredArgsConstructor;
import oneoneone.oneboard.dto.BoardDTO;
import oneoneone.oneboard.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping()
    public String boardMain(){
        return "/board/boardMain";
    }

    @GetMapping("/save")
    public String saveForm() {
        return "/board/boardSave";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "/board/boardMain";
    }
    @GetMapping("/list")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "/board/boardList";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "/board/postDetail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "/board/postUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "/board/postDetail";
//        return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }

}



/*@Controller
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
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "/board/boardList";
    }
    @GetMapping("/board/list/{boardId}")
    public String boardListById(@PathVariable Long boardId,Model model){
        boardService.updateHits(boardId);
        BoardDTO boardDTO = boardService.findById(boardId);
        model.addAttribute("board",boardDTO);
        return "/board/postDetail";
    }

    @GetMapping("/board/update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model){
        BoardDTO boardDTO = boardService.findById(boardId);
        System.out.println("boardId "+ boardDTO.getBoardId());
        model.addAttribute("boardUpdate",boardDTO);
        return "/board/postUpdate";
    }
    //  여기가 문제인듯

    @PostMapping("/board/update")
    public String update(@ModelAttribute BoardDTO boardDTO,Model model){
        BoardDTO board = boardService.update(boardDTO);
        System.out.println("boardId 2번째 호출 "+ board.getBoardId());
        model.addAttribute("board",board);
        return "/board/postDetail";
}

}*/
