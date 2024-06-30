package com.ruoyi.project.system.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysTenant;
import com.ruoyi.project.system.service.ISysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/tenant")
public class SysTenantController extends BaseController
{
    @Autowired
    private ISysTenantService tenantService;

    /**
     * 获取租户列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTenant tenant)
    {
        startPage();
        List<SysTenant> list = tenantService.selectTenantList(tenant);
        return getDataTable(list);
    }

    /**
     * 根据租户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:query')")
    @GetMapping(value = { "/", "/{tenantId}" })
    public AjaxResult getInfo(@PathVariable(value = "tenantId", required = false) Long tenantId)
    {
        AjaxResult ajax = AjaxResult.success();
        if (tenantId != null)
        {
            ajax.put(AjaxResult.DATA_TAG, tenantService.selectTenantById(tenantId));
        }
        return ajax;
    }

    /**
     * 新增租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:add')")
    @Log(title = "租户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysTenant tenant)
    {
        return toAjax(tenantService.insertTenant(tenant));
    }

    /**
     * 修改租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:edit')")
    @Log(title = "租户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysTenant tenant)
    {
        return toAjax(tenantService.updateTenant(tenant));
    }

    /**
     * 删除租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:remove')")
    @Log(title = "租户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tenantIds}")
    public AjaxResult remove(@PathVariable Long[] tenantIds)
    {
        return toAjax(tenantService.deleteTenantByIds(tenantIds));
    }
}