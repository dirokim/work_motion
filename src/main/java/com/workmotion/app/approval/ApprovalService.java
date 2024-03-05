package com.workmotion.app.approval;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.document.util.Pager;
import com.workmotion.app.referrer.ReferrerDTO;

@Service
public class ApprovalService {
	
	@Autowired
	private ApprovalDAO approvalDAO;
	
	public List<ApprovalDTO> approvalGetList(Pager pager)throws Exception{
		
		pager.makeRow();
		
		Long totalCount = approvalDAO.getTotalCount(pager);
		
		pager.makeNum(totalCount);
		
		List<ApprovalDTO> ar = approvalDAO.approvalGetList(pager);
		
		return ar;
		
	}	

}
