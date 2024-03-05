package com.workmotion.app.document;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.approval.ApprovalDTO;
import com.workmotion.app.document.file.DocumentFileDTO;
import com.workmotion.app.document.util.Pager;
import com.workmotion.app.referrer.ReferrerDTO;

@Repository
public class DocumentDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.workmotion.app.document.DocumentDAO.";
	//서류 저장
	public int createDocument(DocumentDTO documentDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"createDocument", documentDTO);
	}
	//서류안 첨부파일 저장
	public int createFiles(DocumentFileDTO fileDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"createFiles", fileDTO);
	}
	//서류안 참조자 저장
	public int createReferrer(ReferrerDTO referrerDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"createReferrer", referrerDTO);
	}
	//내가 보낸 서류 리스트
	public List<DocumentDTO> getDocumentList(Pager pager)throws Exception{
		return sqlSession.selectList(NAMESPACE+"getDocumentList", pager);
	}
	//내가 보낸 서류 리스트 총 페이지
	public Long getTotalCount(Pager pager)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
	}
	//서류안 결재자 저장
	public int createApproval(ApprovalDTO approvalDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"createApproval", approvalDTO);
	}
	//서류 자세히보기
	public DocumentDTO getDetail(DocumentDTO documentDTO)throws Exception{		
		return sqlSession.selectOne(NAMESPACE+"getDetail", documentDTO);
	}
	//서류 자세히보기 참조자 list 
	public List<ReferrerDTO> referrerDetail(DocumentDTO documentDTO)throws Exception{
		return sqlSession.selectList(NAMESPACE+"referrerDetail", documentDTO);		
	}
	//서류 자세히보기 결재자 list
	public List<ApprovalDTO> approvalDetail(DocumentDTO documentDTO)throws Exception{
		return sqlSession.selectList(NAMESPACE+"approvalDetail", documentDTO);
	}

}
