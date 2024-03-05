package com.workmotion.app.position;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.member.MemberDTO;

@Repository
public class PositionDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.workmotion.app.position.PositionDAO.";
	
	public List<PositionDTO> selectPosition (MemberDTO memberDTO) throws Exception {
		return sqlSession.selectList(NAMESPACE+"selectPosition",memberDTO);
	}
	
}
