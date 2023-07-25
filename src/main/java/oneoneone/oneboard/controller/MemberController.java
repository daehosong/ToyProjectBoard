package oneoneone.oneboard.controller;

import lombok.RequiredArgsConstructor;
import oneoneone.oneboard.dto.MemberDTO;
import oneoneone.oneboard.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/save")
    public String memberSaveForm(){
        return "save";
    }

    @PostMapping("/member/save")
    public String memberSave(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        System.out.println("save");
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("/member/login")
    public String memberLogin(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            //  로그인 성공
            session.setAttribute("loginEmail",loginResult.getMemberEmail());
            System.out.println(loginResult.getMemberPassword());
            return "main";
        }else {
            return "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        //  model에 담아서 반환 페이지로 넘겨줌
        model.addAttribute("memberList",memberDTOList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id,Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "memberView";
    }

    @GetMapping("/member/update")
    public String memberUpdateForm(HttpSession session,Model model){
        String myEmail = (String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember",memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/"+memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String memberLogout( HttpSession session){
        //  invalidate()  무효화 , 상쇄
        session.invalidate();
        return "index";
    }

    @ResponseBody
    @PostMapping("/member/email-check")
    public String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmail : "+memberEmail);
        String checkResult=memberService.emailCheck(memberEmail);
        if(checkResult!=null){
            return "ok";
        }
        else {
            return null;
        }
    }

}
