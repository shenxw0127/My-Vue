package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.SysMeeting;

/**
 * 会议信息 数据层
 */
public interface SysMeetingMapper {
    /**
     * 查询会议数据集合
     *
     * @param meeting 会议信息
     * @return 会议数据集合
     */
    List<SysMeeting> selectMeetingList(SysMeeting meeting);

    /**
     * 查询所有会议
     *
     * @return 会议列表
     */
    List<SysMeeting> selectMeetingAll();

    /**
     * 通过会议ID查询会议信息
     *
     * @param meetingId 会议ID
     * @return 会议信息
     */
    SysMeeting selectMeetingById(Long meetingId);

    /**
     * 新增会议信息
     *
     * @param meeting 会议信息
     * @return 结果
     */
    int insertMeeting(SysMeeting meeting);

    /**
     * 修改会议信息
     *
     * @param meeting 会议信息
     * @return 结果
     */
    int updateMeeting(SysMeeting meeting);

    /**
     * 批量删除会议信息
     *
     * @param meetingIds 需要删除的会议ID
     * @return 结果
     */
    int deleteMeetingByIds(Long[] meetingIds);
}