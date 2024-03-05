package com.workmotion.app.tempDoc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.document.DocumentDTO;
import com.workmotion.app.document.util.Pager;

@Service
public class TempDocService {
	
	@Autowired
	private TempDocDAO tempDocDAO;
	
	//임시저장 서류 리스트
	public List<DocumentDTO> getTempDocList(Pager pager)throws Exception{
		
		pager.makeRow();		
		
		Long totalCount = tempDocDAO.getTotalCount(pager);
		
		pager.makeNum(totalCount);
		
		List<DocumentDTO> ar = tempDocDAO.getTempDocList(pager);		
		
		
		return ar;
		
	}

}
