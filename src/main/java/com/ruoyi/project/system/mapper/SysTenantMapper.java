package com.ruoyi.project.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.system.domain.SysTenant;

public interface SysTenantMapper
{
    public List<SysTenant> selectTenantList(SysTenant tenant);

    public SysTenant selectTenantById(Long tenantId);

    public int insertTenant(SysTenant tenant);

    public int updateTenant(SysTenant tenant);

    public int deleteTenantById(Long tenantId);

    public int deleteTenantByIds(Long[] tenantIds);

    public SysTenant checkTenantNameUnique(String tenantName);
}