package com.workmotion.app.position;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.workmotion.app.member.MemberDTO;

@Controller
@RequestMapping(value = "/position/*")
public class PositionController {
	@Autowired
	private PositionService  positionService;
	
	@GetMapping("list")
	public String selectPosition (HttpSession session,Model model) throws Exception {
		MemberDTO memberDTO =(MemberDTO)session.getAttribute("member");
		List<PositionDTO> ar = positionService.selectPosition(memberDTO);
		model.addAttribute("list",ar);
		model.addAttribute("page","position/list");
		return "index";
	}
	
}
