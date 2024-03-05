package com.workmotion.app.referrer;

import com.workmotion.app.document.DocumentDTO;
import com.workmotion.app.member.MemberDTO;

public class ReferrerDTO {
	
	private Long document_id;
	private Long member_id;
	
	private MemberDTO memberDTO;
	private DocumentDTO documentDTO;
	
	
	
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
	public DocumentDTO getDocumentDTO() {
		return documentDTO;
	}
	public void setDocumentDTO(DocumentDTO documentDTO) {
		this.documentDTO = documentDTO;
	}
	public Long getDocument_id() {
		return document_id;
	}
	public void setDocument_id(Long document_id) {
		this.document_id = document_id;
	}
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}

}
