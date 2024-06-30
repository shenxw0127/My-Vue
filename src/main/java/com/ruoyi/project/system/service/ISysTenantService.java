package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysTenant;

import java.util.List;

public interface ISysTenantService {

    /**
     * 根据条件分页查询租户列表
     *
     * @param tenant 租户信息
     * @return 租户信息集合信息
     */
    public List<SysTenant> selectTenantList(SysTenant tenant);

    /**
     * 通过租户ID查询租户
     *
     * @param tenantId 租户ID
     * @return 租户对象信息
     */
    public SysTenant selectTenantById(Long tenantId);

    /**
     * 校验租户名称是否唯一
     *
     * @param tenant 租户信息
     * @return 结果
     */
    public boolean checkTenantNameUnique(SysTenant tenant);

    /**
     * 新增租户信息
     *
     * @param tenant 租户信息
     * @return 结果
     */
    public int insertTenant(SysTenant tenant);

    /**
     * 修改租户信息
     *
     * @param tenant 租户信息
     * @return 结果
     */
    public int updateTenant(SysTenant tenant);

    /**
     * 通过租户ID删除租户
     *
     * @param tenantId 租户ID
     * @return 结果
     */
    public int deleteTenantById(Long tenantId);

    /**
     * 批量删除租户信息
     *
     * @param tenantIds 需要删除的租户ID
     * @return 结果
     */
    public int deleteTenantByIds(Long[] tenantIds);
}