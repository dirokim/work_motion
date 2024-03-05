package com.workmotion.app.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.util.Pager;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	private String namespace= "com.workmotion.app.board.BoardDAO.";
	
	//보더리스트@@@@@@@@@@@@@
	public List<Map<String, Object>> getBoardList(Map<String, Object> map)throws Exception {
		return sqlSession.selectList(namespace+"getBoardList", map);
	}
	//토탈카운트@@@@@@@@@@@@
	public Long totalCount(Pager pager) throws Exception{
		return sqlSession.selectOne(namespace+"totalCount", pager);
		
	}
	
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception{
		return sqlSession.selectOne(namespace+"getBoardDetail", boardDTO);
	}
	
	public int create(BoardDTO boardDTO) throws Exception{
		return sqlSession.insert(namespace+"create", boardDTO);
	}
	
	public int getViews(BoardDTO boardDTO)throws Exception {
		return sqlSession.update(namespace+"getViews", boardDTO);
	}
	
	public int setUpdate(BoardDTO boardDTO) throws Exception{
		return sqlSession.update(namespace+"setUpdate", boardDTO);
	}
	
	public int delete (BoardDTO boardDTO) throws Exception{
		return sqlSession.delete(namespace+"delete", boardDTO);
	}
}
