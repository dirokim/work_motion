package com.workmotion.app.tna;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.member.MemberDTO;

@Service
public class TnaService {
	@Autowired	
	private TnaDAO tnaDAO;
	
	public int getinTna (MemberDTO memberDTO) throws Exception {
		return tnaDAO.getinTna(memberDTO);
	}
	
	public int getoutTna(MemberDTO memberDTO) throws Exception {
		return tnaDAO.getoutTna(memberDTO);
	}
	public List<Map<String,Object>> getTnaDetail(MemberDTO memberDTO) throws Exception {
		return tnaDAO.getTnaDetail(memberDTO);
	}
}
