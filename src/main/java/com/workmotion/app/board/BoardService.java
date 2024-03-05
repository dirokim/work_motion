package com.workmotion.app.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.category.CategoryDTO;
import com.workmotion.app.util.Pager;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	public List<Map<String, Object>> getBoardList(CategoryDTO categoryDTO,Pager pager)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		pager.setCount(categoryDTO.getId());
		pager.makeRow();
		Long totalCount = boardDAO.totalCount(pager);
		pager.makePage(totalCount);
		map.put("category", categoryDTO);
		map.put("pager", pager);
		
		 List<Map<String, Object>> dtos = boardDAO.getBoardList(map);
		 return dtos;
		 
	}
	
	public int getViews(BoardDTO boardDTO) throws Exception{
		return boardDAO.getViews(boardDTO);
	}
	
	public BoardDTO getBoardDetail(BoardDTO boardDTO)throws Exception {
		return boardDAO.getBoardDetail(boardDTO);
	}
	
	public int create(BoardDTO boardDTO) throws Exception{
		return boardDAO.create(boardDTO);
	}
	
	public int setUpdate(BoardDTO boardDTO) throws Exception{
		return boardDAO.setUpdate(boardDTO);
	}
	
	public int delete(BoardDTO boardDTO) throws Exception{
		return boardDAO.delete(boardDTO);
	}
}
