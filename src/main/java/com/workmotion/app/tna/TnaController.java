package com.workmotion.app.tna;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.workmotion.app.hr.HrService;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;

@Controller
@RequestMapping(value = "/tna/*")
public class TnaController {
	@Autowired
	private TnaService tnaService;
	@Autowired
	private HrService hrService;
	
	@GetMapping("detail")
	public String getTnaDetail (MemberDTO memberDTO,Model model,Pager pager) throws Exception {
		memberDTO = hrService.getMemberDetail(memberDTO);
			String sum = "";
		if(pager.getKind() != null) {
			sum = pager.getKind();
		}else {
			sum = "2024-02-01,2024-02-29";
		}
		String [] br = sum.split(",");
		memberDTO.setPhone(br[0]);
		memberDTO.setPassword(br[1]);
		List<Map<String,Object>> ar = tnaService.getTnaDetail(memberDTO);
		memberDTO.setPassword(null);
		model.addAttribute("dto",memberDTO);
		model.addAttribute("page","tna/detail");
		model.addAttribute("list",ar);
		return "index";
	}
	
	@GetMapping("list")
	public String getTnaList (HttpSession session,MemberDTO memberDTO,Model model,Pager pager) throws Exception {
		List<Map<String,Object>> ar = hrService.getMemberList(memberDTO,session,pager);
		model.addAttribute("list",ar);
		model.addAttribute("page","tna/list");
		return "index";
	}
	
	
	//퇴근
	@GetMapping("out")
	public String getoutTna(HttpSession session,Model model) throws Exception {
		MemberDTO m = (MemberDTO)session.getAttribute("member");
		int result = tnaService.getoutTna(m);
		String msg = "퇴근 등록 실패";
		if(result>0) {
			msg = "퇴근 등록 성공";
		}
		model.addAttribute("page","/commons/result");
		model.addAttribute("msg",msg);
		model.addAttribute("path","/");
		return "index";
	}
	
	//출근
	@GetMapping("in")
	public String getinTna(HttpSession session,Model model) throws Exception {
		MemberDTO m = (MemberDTO)session.getAttribute("member");
		int result = tnaService.getinTna(m);
		String msg = "출근 등록 실패";
		if(result>0) {
			msg = "출근 등록 성공";
		}
		
		model.addAttribute("page","/commons/result");
		model.addAttribute("msg",msg);
		model.addAttribute("path","/");
		return "index";
		
	}
	//근태 페이지
	@GetMapping("my")
	public String getmyTna (Model model)throws Exception {
		model.addAttribute("page","/tna/my");
		return "index";
	}
}
