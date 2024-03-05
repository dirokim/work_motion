package com.workmotion.app.approval;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.document.util.Pager;
import com.workmotion.app.referrer.ReferrerDTO;

@Repository
public class ApprovalDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.workmotion.app.approval.ApprovalDAO.";
	
	//참조함 리스트	
	public List<ApprovalDTO> approvalGetList(Pager pager)throws Exception{
		return sqlSession.selectList(NAMESPACE+"approvalGetList", pager);
	}
	
	//총 페이지 수
	public Long getTotalCount(Pager pager)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTotalCount",pager);
	}

}
