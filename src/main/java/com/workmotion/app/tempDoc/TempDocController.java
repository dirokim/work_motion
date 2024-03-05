package com.workmotion.app.tempDoc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.workmotion.app.document.DocumentDTO;
import com.workmotion.app.document.util.Pager;
import com.workmotion.app.member.MemberDTO;

@Controller
@RequestMapping("/tempDoc/*")
public class TempDocController {
	@Autowired
	private TempDocService tempDocService;
	
	@GetMapping("list")
	public String getTempDocList(HttpSession session,Model model,Pager pager)throws Exception{
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		pager.setMember_id(memberDTO.getId());
		
		
		List<DocumentDTO> ar =  tempDocService.getTempDocList(pager);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		model.addAttribute("page", "tempDoc/list");
		
		return "index";
	}
	
	@PostMapping("add")
	public String getTemporarySaveAdd(DocumentDTO documentDTO,MultipartFile[] file,Model model,String referrer,HttpSession session,String approval)throws Exception{
		
		
		
		
		return "index";
	}


}
