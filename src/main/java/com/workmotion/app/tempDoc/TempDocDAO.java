package com.workmotion.app.tempDoc;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.document.DocumentDTO;
import com.workmotion.app.document.util.Pager;

@Repository
public class TempDocDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.workmotion.app.tempDoc.TempDocDAO.";
	
	//내가 임시저장한 서류 리스트
	public List<DocumentDTO> getTempDocList(Pager pager)throws Exception{
		return sqlSession.selectList(NAMESPACE+"getTempDocList", pager);
	}
	//내가 임시저장한 서류 리스트 총 페이지
	public Long getTotalCount(Pager pager)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
	}

}
