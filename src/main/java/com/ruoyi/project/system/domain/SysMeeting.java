package com.ruoyi.project.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 会议表 sys_meeting
 */
public class SysMeeting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 会议ID */
    @Excel(name = "会议ID", cellType = ColumnType.NUMERIC)
    private Long meetingId;

    /** 会议名称 */
    @Excel(name = "会议名称")
    private String meetingName;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    /** 会议状态（0进行中 1已结束） */
    @Excel(name = "状态", readConverterExp = "0=进行中,1=已结束")
    private String status;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 会议内容 */
    private String content;

    /** 会议封面 */
    private String coverImage;

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    @NotBlank(message = "会议名称不能为空")
    @Size(min = 0, max = 50, message = "会议名称长度不能超过50个字符")
    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    @NotBlank(message = "创建人不能为空")
    @Size(min = 0, max = 50, message = "创建人长度不能超过50个字符")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @NotBlank(message = "会议状态不能为空")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotNull(message = "开始时间不能为空")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @NotNull(message = "结束时间不能为空")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @NotBlank(message = "会议内容不能为空")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotBlank(message = "会议封面不能为空")
    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("meetingId", getMeetingId())
                .append("meetingName", getMeetingName())
                .append("creator", getCreator())
                .append("status", getStatus())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("content", getContent())
                .append("coverImage", getCoverImage())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}