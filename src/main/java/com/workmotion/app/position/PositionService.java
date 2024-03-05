package com.workmotion.app.position;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.member.MemberDTO;

@Service
public class PositionService {
	@Autowired
	private PositionDAO positionDAO;

	public List<PositionDTO> selectPosition(MemberDTO memberDTO) throws Exception {
		return  positionDAO.selectPosition(memberDTO);
	}
}
