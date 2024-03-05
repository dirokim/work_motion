package com.workmotion.app.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleDAO scheduleDAO;

    public List<ScheduleDTO> getSchedule(ScheduleDTO scheduleDTO) throws Exception {


        return scheduleDAO.getSchedule(scheduleDTO);
    }

    public int createSchedule(ScheduleDTO scheduleDTO) throws Exception {
        return scheduleDAO.createSchedule(scheduleDTO);
    }


    public int deleteSchedule(ScheduleDTO scheduleDTO) throws Exception {
        return scheduleDAO.deleteSchedule(scheduleDTO);
    }

    public int updateSchedule(ScheduleDTO scheduleDTO) throws Exception {
        return scheduleDAO.updateSchedule(scheduleDTO);
    }
}
