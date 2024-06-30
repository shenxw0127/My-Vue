package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.domain.SysTenant;
import com.ruoyi.project.system.mapper.SysTenantMapper;
import com.ruoyi.project.system.service.ISysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysTenantServiceImpl implements ISysTenantService {

    @Autowired
    private SysTenantMapper tenantMapper;

    @Override
    public List<SysTenant> selectTenantList(SysTenant tenant) {
        return tenantMapper.selectTenantList(tenant);
    }

    @Override
    public SysTenant selectTenantById(Long tenantId) {
        return tenantMapper.selectTenantById(tenantId);
    }

    @Override
    public boolean checkTenantNameUnique(SysTenant tenant) {
        Long tenantId = tenant.getTenantId() == null ? -1L : tenant.getTenantId();
        SysTenant info = tenantMapper.checkTenantNameUnique(tenant.getTenantName());
        if (info != null && info.getTenantId().longValue() != tenantId.longValue()) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public int insertTenant(SysTenant tenant) {
        return tenantMapper.insertTenant(tenant);
    }

    @Override
    @Transactional
    public int updateTenant(SysTenant tenant) {
        return tenantMapper.updateTenant(tenant);
    }

    @Override
    @Transactional
    public int deleteTenantById(Long tenantId) {
        return tenantMapper.deleteTenantById(tenantId);
    }

    @Override
    @Transactional
    public int deleteTenantByIds(Long[] tenantIds) {
        return tenantMapper.deleteTenantByIds(tenantIds);
    }
}