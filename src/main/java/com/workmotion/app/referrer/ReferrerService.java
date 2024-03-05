package com.workmotion.app.referrer;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.document.util.Pager;
import com.workmotion.app.member.MemberDTO;

@Service
public class ReferrerService {
	
	@Autowired
	private ReferrerDAO referrerDAO;
	
	public List<ReferrerDTO> getList(Pager pager)throws Exception{
		
		
		pager.makeRow();
		
		Long totalCount = referrerDAO.getTotalCount(pager);
		
		pager.makeNum(totalCount);
		
		List<ReferrerDTO> ar = referrerDAO.getList(pager);		
		
		return ar;
		
	}	
	

}
