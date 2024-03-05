package com.workmotion.app.department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;

@Controller
@RequestMapping("/department/*")
public class DepartmentController {
  @Autowired	
  private DepartmentService departmentService;
  
  
  //memberUpdate
	@GetMapping("memberupdate")
	public String memberUpdate (String department_name ,MemberDTO memberDTO,Pager pager, Model model)throws Exception {
		
		model.addAttribute("member", memberDTO);
		model.addAttribute("department", department_name);
	 List<MemberDTO> ar = departmentService.getDepartmentDetail(memberDTO,pager);	
		model.addAttribute("detail", ar);
		model.addAttribute("page", "department/memberupdate");
		return "index"; 
		
	}
  
  //getDepartmentList
  	@GetMapping("departmentList")
	public String getDepartmentList(Pager pager, Model model)throws Exception {
		List<MemberDTO> ar = departmentService.getDepartmentList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		model.addAttribute("page", "department/departmentList");
		return "index";		
	}
  	
  	//getDepartmentDetail
	@GetMapping("departmentDetail")
	public String getDepartmentDetail(DepartmentDTO departmentDTO,Pager pager, Model model)throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setDepartment_id(departmentDTO.getId());
		model.addAttribute("department", departmentDTO);
	   List<MemberDTO> dtos = departmentService.getDepartmentDetail(memberDTO,pager);	
		model.addAttribute("detail", dtos);
		model.addAttribute("pager", pager);
		model.addAttribute("page", "department/departmentDetail");
		memberDTO.setDepartment_id(departmentDTO.getId());
		model.addAttribute("member", memberDTO);
		return "index"; 		
	}
	
	
	//getMemberList
	@GetMapping("memberList")
	public String getMemberList(MemberDTO memberDTO,Pager pager, Model model) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pager", pager);
		map.put("member", memberDTO);
		List<Map<String, Object>> dtos = departmentService.getMemberList(map);		
		
		model.addAttribute("member", memberDTO);
		model.addAttribute("list", dtos);
		model.addAttribute("pager", pager);
		model.addAttribute("page", "department/memberList");
		return "index";
	}
	
	
	//updateMember
	@ResponseBody
	@PostMapping("updateMember")
	public int updateMember(Long [] id, Long department_id, Model model) throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setDepartment_id(department_id);
		int result=0;
		for(int i = 0; i<id.length;i++) {
			memberDTO.setId(id[i]);
			 result = departmentService.createMember(memberDTO);
		}
		return result;
	}
	
	
	//deleteMember
	@ResponseBody
	@PostMapping("deleteMember")
	public int deleteMember(Long [] id, Long department_id, Model model) throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setDepartment_id(department_id);
		int result=0;
		for(int i = 0; i<id.length;i++) {
			memberDTO.setId(id[i]);
			 result = departmentService.deleteMember(memberDTO);
		}
		return result;
	}
	
	
	//createDepartment
	@GetMapping("departmentCreate")
	public String createDepartment(Model model) throws Exception{
		model.addAttribute("page", "department/departmentCreate");
		return "index";
	}
	
	
	//createDepartmentPost
	@PostMapping("departmentCreate")
	public String createDepartment(DepartmentDTO departmentDTO, Model model) throws Exception{
		int result = departmentService.createDepartment(departmentDTO);
		String msg = "실패";
		if(result>0) {
			msg = "성공";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("path", "./departmentList");
		return "commons/result";
		
	}
	
	
	//updateDepartment
	@GetMapping("departmentUpdate")
	public String updateDepartment(DepartmentDTO departmentDTO, Model model)throws Exception{
		departmentDTO = departmentService.departmentDetail(departmentDTO);
		model.addAttribute("update", departmentDTO);
		model.addAttribute("page", "/department/departmentUpdate");
		return "index";
	}
	
	//updateDepartmentPost
	@PostMapping("departmentUpdate")
	public String updateDepartment(DepartmentDTO departmentDTO, Model model,MemberDTO memberDTO)throws Exception{
		int result = departmentService.updateDepartment(departmentDTO);
		
		String msg = "실패";
		if(result>0) {
			msg = "성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", "./departmentList");
		return "commons/result";
	}
	
	
	//deleteDepartmentPost
	@ResponseBody
	@PostMapping("departmentDelete")
	public int deleteDepartment(Long [] id, Model model)throws Exception {
		int result=0;
	DepartmentDTO departmentDTO = new DepartmentDTO();
	
		for(Long a : id) {
			departmentDTO.setId(a);
			result = departmentService.deleteDepartment(departmentDTO);
		}
		return result;
	}
}
