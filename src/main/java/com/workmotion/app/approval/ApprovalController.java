package com.workmotion.app.approval;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.workmotion.app.document.util.Pager;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.referrer.ReferrerDTO;

@Controller
@RequestMapping("/approval/*")
public class ApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	@GetMapping("list")
	public String approvalGetList(HttpSession session,Model model,Pager pager)throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		pager.setMember_id(memberDTO.getId());		
		
		List<ApprovalDTO> ar = approvalService.approvalGetList(pager);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);		
		model.addAttribute("page", "approval/list");
		
		return "index";
		
		
	}

}
