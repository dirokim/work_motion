package com.workmotion.app.department;

import com.workmotion.app.member.MemberDTO;

import java.util.List;

public class DepartmentDTO {
    private Long id;
    private String name;
    private String phone_num;
    private List<MemberDTO> memberDTOs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public List<MemberDTO> getMemberDTOs() {
        return memberDTOs;
    }

    public void setMemberDTOs(List<MemberDTO> memberDTOs) {
        this.memberDTOs = memberDTOs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
