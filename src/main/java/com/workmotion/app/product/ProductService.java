package com.workmotion.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workmotion.app.company.CompanyDTO;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;

@Service
public class ProductService {
	@Autowired
	private ProductDAO productDAO;
	
	public List<ProductDTO> getList(Pager pager)throws Exception{
		pager.makeRow();
		Long totalCount = productDAO.getTotalCount(pager);
		pager.makePage(totalCount);
		
		return productDAO.getList(pager);
	}
	
	public ProductDTO getDetail(ProductDTO productDTO)throws Exception {
		return productDAO.getDetail(productDTO);
	}
	
	public CompanyDTO getPaymentDetail(MemberDTO memberDTO) throws Exception{
		return productDAO.getPaymentDetail(memberDTO);
	}
	
	
}
