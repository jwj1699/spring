package com.spring.pjt04;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

    private BoardService service;

    @GetMapping("/list")
    public void list(Criteria cri, Model model){
        log.info("/list" + cri);

        int total = service.getTotal(cri);
        model.addAttribute("list", service.getList(cri));
        model.addAttribute("pageMaker",new PageDTO(cri, total));
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr){
        log.info("/register " + board);

        service.register(board);
        rttr.addFlashAttribute("result", board.getBno());
        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})                //ModelAttribute 지정한 이름으로 자동으로 모델에 담아주는 어노테이션
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model){
        log.info("/get or /modify");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("modify:" + board);

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        /*UriComponentsBuilder 사용으로 간편하게 해결됨
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());*/
        return "redirect:/board/list" + cri.getListLink();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        log.info("/remove");

        if(service.remove(bno)){
            rttr.addFlashAttribute("result", "sucess");
        }
        
        return "redirect:/board/list" + cri.getListLink();
    }



}
