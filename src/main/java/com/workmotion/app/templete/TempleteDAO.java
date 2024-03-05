package com.workmotion.app.templete;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.document.util.Pager;
import com.workmotion.app.member.MemberDTO;

@Repository
public class TempleteDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.workmotion.app.templete.TempleteDAO.";
	
	//서류종류 리스트 
	public List<TempleteDTO> getTempleteList(Pager pager)throws Exception{
		return sqlSession.selectList(NAMESPACE+"getTempleteList", pager);		
	}	
	public Long getTotalCount(Pager pager)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
	}
	
	//서류종류 추가
	public int createTempleteAdd(TempleteDTO templeteDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"createTempleteAdd", templeteDTO);
	}
	//서류종류 디테일
	public TempleteDTO getTempleteDetail(TempleteDTO templeteDTO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTempleteDetail", templeteDTO);
	}
	
	// 직원 리스트 
	public List<MemberDTO> getReferrerList(Pager pager)throws Exception{
		return sqlSession.selectList(NAMESPACE+"getReferrerList", pager);
	}
	
	public Long getReferrerTotalCount(Pager pager)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getReferrerTotalCount", pager);
	} 

	

}
