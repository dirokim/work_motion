package com.workmotion.app.schedule;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleDAO {
    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE = "com.workmotion.app.schedule.ScheduleDAO.";

    public int createSchedule(ScheduleDTO scheduleDTO) throws Exception {
        return sqlSession.insert(NAMESPACE + "createSchedule", scheduleDTO);
    }

    public List<ScheduleDTO> getSchedule(ScheduleDTO scheduleDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getSchedule", scheduleDTO);
    }

    public int deleteSchedule(ScheduleDTO scheduleDTO) throws Exception {
        return sqlSession.delete(NAMESPACE + "deleteSchedule", scheduleDTO);
    }

    public int updateSchedule(ScheduleDTO scheduleDTO) throws Exception {
        return sqlSession.update(NAMESPACE + "updateSchedule", scheduleDTO);
    }
}
