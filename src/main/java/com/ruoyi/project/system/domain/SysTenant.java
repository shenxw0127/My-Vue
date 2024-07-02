package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Size;

public class SysTenant {
    private String tenantName;
    private String contactPerson;
    private String phoneNumber;
    private String admin;
    private Long tenantId;
    private SysUser adminUser;
    private String icon;
    private String remark;


    // getters and setters
    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public SysUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(SysUser adminUser) {
        this.adminUser = adminUser;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tenantName", getTenantName())
                .append("contactPerson", getContactPerson())
                .append("phoneNumber", getPhoneNumber())
                .append("admin", getAdmin())
                .append("tenantId", getTenantId())
                .toString();
    }
}