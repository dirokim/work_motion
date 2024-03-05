package com.workmotion.app.project.dao;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.model.TaskDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TaskDAO {
    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE = "com.workmotion.app.project.dao.TaskDAO.";

    public List<TaskDTO> getTaskList(Map<String, Object> map) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getTaskList", map);
    }

    public int createTask(TaskDTO taskDTO) throws Exception {
        return sqlSession.insert(NAMESPACE + "createTask", taskDTO);
    }

    public int updateTask(TaskDTO taskDTO) throws Exception {
        return sqlSession.update(NAMESPACE + "updateTask", taskDTO);
    }

    public int deleteTask(TaskDTO taskDTO) throws Exception {
        return sqlSession.delete(NAMESPACE + "deleteTask", taskDTO);
    }

    public TaskDTO getTaskDetail(TaskDTO taskDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getTaskDetail", taskDTO);
    }

    public int addCharge(Map<String, Object> map) throws Exception {
        return sqlSession.insert(NAMESPACE + "addCharge", map);
    }

    public List<MemberDTO> getCharge(TaskDTO taskDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getCharge", taskDTO);
    }

    public int changeStatus(TaskDTO taskDTO) throws Exception {
        return sqlSession.update(NAMESPACE + "changeStatus", taskDTO);
    }

    public int removeCharge(TaskDTO taskDTO) throws Exception {
        return sqlSession.delete(NAMESPACE + "removeCharge", taskDTO);
    }
}
