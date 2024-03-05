package com.workmotion.app.tosspayment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TossPaymentDAO {
	@Autowired
	private SqlSession sqlSession;
	private String namespace="com.workmotion.app.tosspayment.TossPaymentDAO.";
	
	
	public int createTossPayment(TossPaymentDTO tossPaymentDTO) throws Exception{
		return sqlSession.insert(namespace+"createTossPayment", tossPaymentDTO);
		
	}
	
	public TossPaymentDTO getTossPaymentDetail(TossPaymentDTO tossPaymentDTO) throws Exception{
		return sqlSession.selectOne(namespace+"getTossPaymentDetail", tossPaymentDTO);
	}
	
	public int updateTossPayment(TossPaymentDTO tossPaymentDTO) throws Exception{
		return sqlSession.update(namespace+"updateTossPayment", tossPaymentDTO);
		
	}
}
