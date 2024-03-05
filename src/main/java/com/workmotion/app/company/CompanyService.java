package com.workmotion.app.company;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	
	//컴퍼니 리스트@@@@@@@@@@@@@@
	public List<CompanyDTO> getCompanyList(Pager pager)throws Exception{
		pager.makeRow();
		Long totalCount = companyDAO.companyTotalCount(pager);
		pager.makePage(totalCount);
		UUID uuid = UUID.randomUUID();
		
		return companyDAO.getCompanyList(pager);
	}
	
	//컴퍼니 업데이트@@@@@@@@@@@@
	public int CompanyUpdate(CompanyDTO companyDTO) throws Exception{
		return companyDAO.companyUpdate(companyDTO);
	}
	
	//컴퍼니 추가@@@@@@@@@@@@@@
	public int createCompany(CompanyDTO companyDTO)throws Exception {
		UUID uuid = UUID.randomUUID();
		String uuidval = uuid.toString();
		companyDTO.setCustomerkey(uuidval);
		return companyDAO.createCompany(companyDTO);
	}
	
	//컴퍼니 디테일@@@@@@@
	public CompanyDTO getCompanyDetail(CompanyDTO companyDTO)throws Exception {
		return companyDAO.getCompanyDetail(companyDTO);
	}
	
	//컴퍼니 삭제@@@@@@@@@
	public int companyDelete(CompanyDTO companyDTO) throws Exception{
		return companyDAO.companyDelete(companyDTO);
				
	}
	//컴퍼니 디테일@@@@@@@@@@@
	public CompanyDTO companyDetail(CompanyDTO companyDTO)throws Exception {
		return companyDAO.getCompanyDetail(companyDTO);
	}
	
	//멤버 토탈카운트 @@@@@@@@@@@@@
	public Long memberTotalCount(Long totalCount) throws Exception{
		return companyDAO.memberTotalCount(totalCount);
	}
}
