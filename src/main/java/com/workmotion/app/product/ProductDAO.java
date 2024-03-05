package com.workmotion.app.product;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.workmotion.app.company.CompanyDTO;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSession sqlSession;
	private String namespace = "com.workmotion.app.product.ProductDAO.";
	
	
	public List<ProductDTO> getList(Pager pager) throws Exception{
		return sqlSession.selectList(namespace+"getList", pager);
		
	}
	
	public Long getTotalCount(Pager pager) throws Exception{
		return sqlSession.selectOne(namespace+"getTotalCount", pager);
	}
	
	public ProductDTO getDetail(ProductDTO productDTO) throws Exception{
		return sqlSession.selectOne(namespace+"getDetail",productDTO);
	}
	
	public CompanyDTO getPaymentDetail(MemberDTO memberDTO) throws Exception{
		return sqlSession.selectOne(namespace+"getPaymentDetail", memberDTO);
	}
	
}
