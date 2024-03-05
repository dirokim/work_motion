package com.workmotion.app.document;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.workmotion.app.approval.ApprovalDTO;
import com.workmotion.app.document.file.DocumentFileDTO;
import com.workmotion.app.document.util.FileManager;
import com.workmotion.app.document.util.Pager;
import com.workmotion.app.referrer.ReferrerDTO;

@Service
public class DocumentService {
	
	@Autowired
	private DocumentDAO documentDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private ServletContext servletContext;
	
	
	//서류 자세히보기
	public DocumentDTO getDetail(DocumentDTO documentDTO)throws Exception{		
		try {
		documentDTO = documentDAO.getDetail(documentDTO);				
		documentDTO.setApprovalDTOs(documentDAO.approvalDetail(documentDTO));		
		documentDTO.setReferrerDTOs(documentDAO.referrerDetail(documentDTO));		
		
		System.out.println(documentDTO.getReferrerDTOs().size());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return documentDTO;
		
	}
	
	//서류 저장 
	public int createDocument(DocumentDTO documentDTO,MultipartFile[] file,String referrer,String approval)throws Exception{
		
		int result = documentDAO.createDocument(documentDTO);
		
		String path = servletContext.getRealPath("/resources/upload/documentFiles");
		
		for(MultipartFile f:file) {
			
			if(f.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(path, f);
			DocumentFileDTO fileDTO = new DocumentFileDTO();
			fileDTO.setName(fileName);
			fileDTO.setOri_name(f.getOriginalFilename());
			fileDTO.setDocument_id(documentDTO.getId());
			result = documentDAO.createFiles(fileDTO);
		}		
		
		String[] referrers = referrer.split(",");
		if(referrers.length > 0 ) {
			for(String r:referrers) {
				ReferrerDTO dto = new ReferrerDTO();
				dto.setDocument_id(documentDTO.getId());
				dto.setMember_id(Long.parseLong(r));
				result = documentDAO.createReferrer(dto);
			}
		}
		String[] approvals = approval.split(",");
		if(approvals.length>0) {
			for(String a:approvals) {
				ApprovalDTO dto = new ApprovalDTO();
				dto.setDocument_id(documentDTO.getId());
				dto.setMember_id(Long.parseLong(a));
				
				result = documentDAO.createApproval(dto);
			}
		}
		
		
		
		
		return result;
	} 
	//보낸 서류 리스트
	public List<DocumentDTO> getDocumentList(Pager pager)throws Exception{
		
		pager.makeRow();		
		
		Long totalCount = documentDAO.getTotalCount(pager);
		
		pager.makeNum(totalCount);
		
		List<DocumentDTO> ar = documentDAO.getDocumentList(pager);
		
		
		
		return ar;
		
	}

	

}
