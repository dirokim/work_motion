package com.workmotion.app.templete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.workmotion.app.document.util.FileManager;
import com.workmotion.app.document.util.Pager;
import com.workmotion.app.member.MemberDTO;

@Service
public class TempleteService {
	
	@Autowired
	private TempleteDAO templeteDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private FileManager fileManager;
	
	//사원리스트 
	public List<MemberDTO> getReferrerList(Pager pager)throws Exception{
		
		pager.makeRow();
		Long totalCount = templeteDAO.getReferrerTotalCount(pager);		
		
		pager.makeNum(totalCount);
		List<MemberDTO> ar = templeteDAO.getReferrerList(pager);		

				
		return ar;		
		
	}
	
	//서류종류 리스트 
	public List<TempleteDTO> getTempleteList(Pager pager)throws Exception{
		
		pager.makeRow();
		Long totalCount = templeteDAO.getTotalCount(pager);
		pager.makeNum(totalCount);
		
		List<TempleteDTO> ar = templeteDAO.getTempleteList(pager);
		
		return ar;
		
	}
	//서류종류 추가 
	public int createTempleteAdd(TempleteDTO templeteDTO,MultipartFile templeteFile)throws Exception{
		
		if(templeteFile.isEmpty()) {
			return 0;
		}
				
		String path = servletContext.getRealPath("/resources/upload/templete");		
		
		String fileName = fileManager.fileSave(path, templeteFile);
		
		templeteDTO.setName(fileName);
		//templeteDTO.setFile_nm(templeteFile.getOriginalFilename());
		
		// templeteFile의 확장자를 제외한 파일 이름을 가져오는 방법
		String originalFilename = templeteFile.getOriginalFilename();
		int lastIndexOfDot = originalFilename.lastIndexOf('.');
		//삼항 연산식 활용 index 가 -1이면 확장자가 없는 파일 그러므로 확장자가 없는 파일이면 그대로 파일저장, 있으면 substring(저장시작,저장끝)해서 제외 후 저장 
		String fileNameWithoutExtension = (lastIndexOfDot == -1) ? originalFilename : originalFilename.substring(0, lastIndexOfDot);

		// templeteDTO에 파일 이름(확장자 제외) 설정
		templeteDTO.setFile_nm(fileNameWithoutExtension);
		
		int result = templeteDAO.createTempleteAdd(templeteDTO);
		
		
		return result;
		
	} 
	//서류 디테일 
	public TempleteDTO getTempleteDetail(TempleteDTO templeteDTO)throws Exception{		
		
		templeteDTO = templeteDAO.getTempleteDetail(templeteDTO);
		
		return templeteDTO;
	}
	

	

}
