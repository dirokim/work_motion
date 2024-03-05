package com.workmotion.app.company;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;

@Repository
public class CompanyDAO {
	@Autowired
	private SqlSession sqlSession;
	private String namespace = "com.workmotion.app.company.CompanyDAO.";
	//컴퍼니 리스트@@@@@@@@@@@@@@
	public List<CompanyDTO> getCompanyList(Pager pager)throws Exception{
		return sqlSession.selectList(namespace+"getCompanyList", pager);
		
	}
	
	//컴퍼니 업데이트@@@@@@@@@@@@@@
	public int companyUpdate(CompanyDTO companyDTO) throws Exception{
		return sqlSession.update(namespace+"companyUpdate", companyDTO);
	}

	
	//컴퍼니 추가@@@@@@@@@@@@@@@@
	public int createCompany(CompanyDTO companyDTO)throws Exception {
		return sqlSession.insert(namespace+"createCompany", companyDTO);
	}
	
	//컴퍼니 디테일@@@@@@@@@@@@@@@@@@
	public CompanyDTO getCompanyDetail(CompanyDTO companyDTO) throws Exception{
		return sqlSession.selectOne(namespace+"getCompanyDetail", companyDTO);
	}
	
	//컴퍼니 삭제@@@@@@@@@@@@@@@@
	public int companyDelete(CompanyDTO companyDTO) throws Exception{
		return sqlSession.delete(namespace+"companyDelete", companyDTO);
		
	}
	
	//멤버 토탈카운트@@@@@@@@@@@@@@@@@@
	public Long memberTotalCount(Long totalCount)throws Exception {
		return sqlSession.selectOne(namespace+"memberTotalCount", totalCount);
	}
	
	//컴퍼니 토탈 카운트@@@@@@@@@@@@@@@@@@@
	public Long companyTotalCount(Pager pager) throws Exception{
		return sqlSession.selectOne(namespace+"companyTotalCount", pager);
	}
	
}
