package com.workmotion.app.hr;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.department.DepartmentDTO;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.position.PositionDTO;

@Repository
public class HrDAO {

	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.workmotion.app.hr.HrDAO.";
	
	public Long totalCount(Map<String,Object> map) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"totalCount", map);
	}
	
	public List<Map<String,Object>> getMemberList (Map<String,Object> map) throws Exception {
		return sqlSession.selectList(NAMESPACE+"getMemberList",map);
	}
	
	public MemberDTO getMemberDetail (MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getMemberDetail",memberDTO);
	}
	
	public MemberDTO getListDetail(MemberDTO memberDTO)throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getMemberDetail",memberDTO);
	}
	public int updateMember(MemberDTO memberDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"updateMember",memberDTO);
	}
	public int deleteMember (MemberDTO memberDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteMember",memberDTO);
	}
	public int createMember(MemberDTO memberDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"createMember",memberDTO);
	}
}
