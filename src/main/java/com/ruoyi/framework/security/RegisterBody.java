package com.ruoyi.framework.security;

/**
 * 用户注册对象
 * 
 * @author ruoyi
 */
public class RegisterBody extends LoginBody
{
    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 性别
     */
    private String sex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 联系人
     */

    private String contactPerson;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex){
        this.sex=sex;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }

    public String getTenantName() {
        return this.tenantName;
    }

    public void setTenantName(String tenantName){
        this.tenantName=tenantName;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public void setContactPerson(String contactPerson){
        this.contactPerson=contactPerson;
    }

}
