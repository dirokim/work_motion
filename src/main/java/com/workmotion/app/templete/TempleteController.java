package com.workmotion.app.templete;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import com.workmotion.app.document.util.Pager;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.position.PositionDTO;

@Controller
@RequestMapping("/docTemplete/*")
public class TempleteController {
	
	@Autowired
	private TempleteService templeteService;
	
	//문서 종류 자세히보기 
	@GetMapping("detail")
	public String getTempleteDetail(TempleteDTO templeteDTO,Model model,HttpSession session,Pager pager)throws Exception{
			
		//이름,부서 session 에서 꺼내기
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");		
		
		//서류종류 디테일
		templeteDTO = templeteService.getTempleteDetail(templeteDTO);		
		model.addAttribute("dto", templeteDTO);
		model.addAttribute("member", memberDTO);
		
		//직원리스트 
		List<MemberDTO> ar = templeteService.getReferrerList(pager);				
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		
		model.addAttribute("page","docTemplete/detail");	
		return "index";
	}
	
	
	
	//참조자 리스트
	@GetMapping("referrer")
	public String getReferrerList(Pager pager,Model model)throws Exception{
				
		List<MemberDTO> ar = templeteService.getReferrerList(pager);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);	
		
				
		return "docTemplete/referrer";
	}	
	
	
	
	//결재자 리스트
	@GetMapping("approval")
	public String getApprovalList(Pager pager,Model model)throws Exception{
		
		
		List<MemberDTO> ar = templeteService.getReferrerList(pager);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);	
		
				
		return "docTemplete/approval";
	}
	
	
	
	
	//문서종류 리스트 
	@GetMapping("list")
	public String getTempleteList(Pager pager,Model model)throws Exception {
		
		List<TempleteDTO> ar = templeteService.getTempleteList(pager);		
		model.addAttribute("templeteList", ar);
		model.addAttribute("pager",pager);
		model.addAttribute("page", "docTemplete/list");
		
		return "index";
		
	}
	
	
	
	
	
	//문서 종류 추가 
	@GetMapping("add")
	public String createTempleteAdd(Model model)throws Exception{
		model.addAttribute("page","docTemplete/add");
		return "index";
	}
	//문서 종류 추가 
	@PostMapping("add")
	public String createTempleteAdd(Model model,TempleteDTO templeteDTO,MultipartFile templeteFile)throws Exception{
				
		int result = templeteService.createTempleteAdd(templeteDTO, templeteFile);
		
		String msg = "등록 실패";
		
		if(result>0) {
			msg = "등록 성공";
			
		}
		model.addAttribute("msg", msg);
		model.addAttribute("path","./list");
		return "commons/result";
		
	}
	

	
}
