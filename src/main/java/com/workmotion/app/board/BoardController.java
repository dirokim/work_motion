package com.workmotion.app.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.workmotion.app.category.CategoryDTO;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@GetMapping("list")
	public String getBoardList(CategoryDTO categoryDTO, Pager pager, Model model) throws Exception {

		

		model.addAttribute("category_id", categoryDTO.getId());
		List<Map<String, Object>> dtos = boardService.getBoardList(categoryDTO, pager);
		
		String name = (String)dtos.get(0).get("NAME");
		model.addAttribute("name", name);
		model.addAttribute("list", dtos);
		model.addAttribute("pager", pager);
		model.addAttribute("page", "board/list");
		if(dtos.size() != 0) {
		model.addAttribute("category_id", dtos.get(0).get("CATEGORY_ID"));
		}
		return "index";
	}

	@GetMapping("detail")
	public String getBoardDetail(BoardDTO boardDTO,Long category_ID, Model model) throws Exception {	
		boardDTO = boardService.getBoardDetail(boardDTO);
		if(boardDTO.getViews() != null) {
		boardDTO.setViews(boardDTO.getViews()+1);
		}
		boardService.getViews(boardDTO);
		
		model.addAttribute("category_ID", category_ID);
		model.addAttribute("detail", boardDTO);
		model.addAttribute("page", "board/detail");
		
		return "index";

	}
	
	@GetMapping("create")
	public String create(HttpSession session,CategoryDTO categoryDTO, Model model) throws Exception {
		MemberDTO dto =(MemberDTO) session.getAttribute("member");
		if(categoryDTO.getId() == 3 ) {
			if(dto.getRole_id() == 40 || dto.getRole_id() == 30 ){
			
			}else {
				model.addAttribute("msg","ㄲㅉ");
				model.addAttribute("path", "/");
				return "/commons/result";
				
			}
		}		
		
		model.addAttribute("category", categoryDTO);
		model.addAttribute("page", "board/create");

		return "index";

	}
	
	@PostMapping("create")
	public String create(HttpSession session,BoardDTO boardDTO,Model model) throws Exception{
	
	MemberDTO memberDTO	= (MemberDTO)session.getAttribute("member");
		 int result = boardService.create(boardDTO);
		
		String msg = "실패";
		if (result > 0) {
			msg = "성공";
		}
		model.addAttribute("member", memberDTO);
		model.addAttribute("msg", msg);
		model.addAttribute("path", "/board/list?id="+boardDTO.getCategory_id());
		
		return "commons/result";
		
	}
	
	
	@GetMapping("update")
	public String setUpdate(HttpSession session,BoardDTO boardDTO, Model model) throws Exception {
		boardDTO = boardService.getBoardDetail(boardDTO);
		MemberDTO dto =(MemberDTO) session.getAttribute("member");
		
		if(boardDTO.getCategory_id() == 3 ) {
			if(dto.getRole_id() == 40 || dto.getRole_id() == 30 ){
			
			}else {
				model.addAttribute("msg","권한이 없습니다");
				model.addAttribute("path", "/");
				return "/commons/result";
				
			}		  	
		}
		model.addAttribute("update", boardDTO);
		model.addAttribute("page", "board/update");
		
		return "index";
	}
	@PostMapping("update")
	public String setUpdatePost(HttpSession session,BoardDTO boardDTO, Model model) throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		int result = boardService.setUpdate(boardDTO);
		
		String msg = "실패";
		if (result > 0) {
			msg = "성공";
		}
		model.addAttribute("member", memberDTO);
		model.addAttribute("msg", msg);
		model.addAttribute("path", "/board/detail?id="+boardDTO.getId());
		
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String delete(BoardDTO boardDTO,Long category_ID,Model model) throws Exception{
		int result = boardService.delete(boardDTO);
		
		String msg = "실패";
		if (result > 0) {
			msg = "성공";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("path", "/board/list?id="+category_ID);
		
		return "commons/result";
	}
	

}
