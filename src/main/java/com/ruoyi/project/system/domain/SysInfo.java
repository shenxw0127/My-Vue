package com.ruoyi.project.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 资讯表 sys_info
 */
public class SysInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 资讯ID */
    @Excel(name = "资讯ID", cellType = ColumnType.NUMERIC)
    private Long infoId;

    /** 资讯标题 */
    @Excel(name = "资讯标题")
    private String title;

    /** 资讯简介 */
    @Excel(name = "资讯简介")
    private String summary;

    /** 资讯图片 */
    @Excel(name = "资讯图片")
    private String image;

    /** 资讯内容 */
    @Excel(name = "资讯内容")
    private String content;

    /** 资讯作者 */
    @Excel(name = "资讯作者")
    private String author;

    /** 租户 */
    @Excel(name = "租户")
    private String tenant;

    // getters and setters

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    @NotBlank(message = "资讯标题不能为空")
    @Size(min = 0, max = 100, message = "资讯标题长度不能超过100个字符")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(min = 0, max = 500, message = "资讯简介长度不能超过500个字符")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotBlank(message = "资讯作者不能为空")
    @Size(min = 0, max = 100, message = "资讯作者长度不能超过100个字符")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @NotBlank(message = "租户不能为空")
    @Size(min = 0, max = 100, message = "租户长度不能超过100个字符")
    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("infoId", getInfoId())
                .append("title", getTitle())
                .append("summary", getSummary())
                .append("image", getImage())
                .append("content", getContent())
                .append("author", getAuthor())
                .append("tenant", getTenant())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
