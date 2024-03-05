package com.workmotion.app.referrer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.document.util.Pager;

@Repository
public class ReferrerDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.workmotion.app.referrer.ReferrerDAO.";
	
	//참조함 리스트	
	public List<ReferrerDTO> getList(Pager pager)throws Exception{		
		
		List<ReferrerDTO> ar = sqlSession.selectList(NAMESPACE+"getList", pager);		
		return ar;
	}
	
	//총 페이지 수
	public Long getTotalCount(Pager pager)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTotalCount",pager);
	}
		
		
		

}
