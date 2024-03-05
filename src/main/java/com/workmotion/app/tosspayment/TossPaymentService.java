package com.workmotion.app.tosspayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TossPaymentService {
	@Autowired
	private TossPaymentDAO tossPaymentDAO;
	
	
	
	public int createTossPayment(TossPaymentDTO tossPaymentDTO) throws Exception{
		return tossPaymentDAO.createTossPayment(tossPaymentDTO);
	}
	
	public TossPaymentDTO getTossPaymentDetail(TossPaymentDTO tossPaymentDTO) throws Exception{
		return tossPaymentDAO.getTossPaymentDetail(tossPaymentDTO);
	}
	
	public int updateTossPayment(TossPaymentDTO tossPaymentDTO) throws Exception{
		return tossPaymentDAO.updateTossPayment(tossPaymentDTO);
	}
	
 
}
