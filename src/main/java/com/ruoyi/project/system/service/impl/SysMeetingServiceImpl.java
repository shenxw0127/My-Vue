package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.domain.SysMeeting;
import com.ruoyi.project.system.mapper.SysMeetingMapper;
import com.ruoyi.project.system.service.ISysMeetingService;

/**
 * 会议信息 服务层处理
 */
@Service
public class SysMeetingServiceImpl implements ISysMeetingService {
    @Autowired
    private SysMeetingMapper meetingMapper;

    @Override
    public List<SysMeeting> selectMeetingList(SysMeeting meeting) {
        return meetingMapper.selectMeetingList(meeting);
    }

    @Override
    public List<SysMeeting> selectMeetingAll() {
        return meetingMapper.selectMeetingAll();
    }

    @Override
    public SysMeeting selectMeetingById(Long meetingId) {
        return meetingMapper.selectMeetingById(meetingId);
    }

    @Override
    public int insertMeeting(SysMeeting meeting) {
        return meetingMapper.insertMeeting(meeting);
    }

    @Override
    public int updateMeeting(SysMeeting meeting) {
        return meetingMapper.updateMeeting(meeting);
    }

    @Override
    public int deleteMeetingByIds(Long[] meetingIds) {
        return meetingMapper.deleteMeetingByIds(meetingIds);
    }
}