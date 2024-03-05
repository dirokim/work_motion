package com.workmotion.app.department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDAO departmentDAO;
		
	//리스트 리스트 리스트@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public List<MemberDTO> getDepartmentList(Pager pager)throws Exception{
			pager.makeRow();
			
			Long totalCount = departmentDAO.getTotalCount(pager);
			pager.makePage(totalCount);
			
			
		return departmentDAO.getDepartmentList(pager);		
		}
	
	//디테일 디테일 디테일@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public List<MemberDTO> getDepartmentDetail(MemberDTO memberDTO, Pager pager) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		pager.makeRow();
		Pager countPager = new Pager();
		countPager.setSearch(pager.getSearch());
		countPager.setPage(memberDTO.getDepartment_id());
		Long totalCount = departmentDAO.getMemberTotalCount(countPager);
		pager.makePage(totalCount);
		map.put("member", memberDTO);
		map.put("Pager", pager);
		
		
		return departmentDAO.getDepartmentDetail(map);
		
	}
	
	public List<Map<String, Object>> getMemberList(Map<String, Object> map)throws Exception{
		Pager pager = (Pager)map.get("pager");
		pager.makeRow();
		map.put("pager", pager);
//		MemberDTO memberDTO = (MemberDTO)map.get("member");
		 Long totalCount = departmentDAO.getMemberListTotalCount(map);
		 pager.makePage(totalCount);
		 map.put("pager", pager);
	 
		
		 return departmentDAO.getMemberList(map);
	}
	
	public int createMember(MemberDTO memberDTO) throws Exception{
		return departmentDAO.createMember(memberDTO);
	}
	
	public int deleteMember(MemberDTO memberDTO)throws Exception{
		return departmentDAO.deleteMember(memberDTO);
	}
	
	public int createDepartment(DepartmentDTO departmentDTO) throws Exception{
		return departmentDAO.createDepartment(departmentDTO);
	}
	
	public int updateDepartment(DepartmentDTO departmentDTO)throws Exception{
		return departmentDAO.updateDepartment(departmentDTO);
	}
	
	public int deleteDepartment(DepartmentDTO departmentDTO) throws Exception{
		return departmentDAO.deleteDepartment(departmentDTO);
	}
	
	public DepartmentDTO departmentDetail(DepartmentDTO departmentDTO)throws Exception {
		return departmentDAO.departmentDetail(departmentDTO);
	}
	
}
