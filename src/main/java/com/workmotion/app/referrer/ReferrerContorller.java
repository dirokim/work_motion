package com.workmotion.app.referrer;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.workmotion.app.document.util.Pager;
import com.workmotion.app.member.MemberDTO;

@Controller
@RequestMapping("/referrer/*")
public class ReferrerContorller {
	
	@Autowired
	private ReferrerService referrerService;
	
	@GetMapping("list")
	public String getList(HttpSession session,Model model,Pager pager)throws Exception{
		
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		pager.setMember_id(memberDTO.getId());		
		
		List<ReferrerDTO> ar = referrerService.getList(pager);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);		
		model.addAttribute("page", "referrer/list");
		
		return "index";
		
		
	}
		


}
