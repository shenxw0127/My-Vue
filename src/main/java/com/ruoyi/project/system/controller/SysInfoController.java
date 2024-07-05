package com.ruoyi.project.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysInfo;
import com.ruoyi.project.system.service.ISysInfoService;

/**
 * 资讯信息操作处理
 */
@RestController
@RequestMapping("/system/info")
public class SysInfoController extends BaseController {
    @Autowired
    private ISysInfoService infoService;

    /**
     * 获取资讯列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysInfo info) {
        startPage();
        List<SysInfo> list = infoService.selectInfoList(info);
        return getDataTable(list);
    }

    @Log(title = "资讯管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysInfo info) {
        List<SysInfo> list = infoService.selectInfoList(info);
        ExcelUtil<SysInfo> util = new ExcelUtil<SysInfo>(SysInfo.class);
        util.exportExcel(response, list, "资讯数据");
    }

    /**
     * 根据资讯编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable Long infoId) {
        return success(infoService.selectInfoById(infoId));
    }

    /**
     * 新增资讯
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "资讯管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysInfo info) {
        if (!infoService.checkInfoTitleUnique(info)) {
            return error("新增资讯'" + info.getTitle() + "'失败，资讯标题已存在");
        }
        info.setCreateBy(getUsername());
        return toAjax(infoService.insertInfo(info));
    }

    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "资讯管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysInfo info) {

        if (StringUtils.isEmpty(info.getTenant())) {
            return error("租户不能为空");
        }
        info.setUpdateBy(getUsername());
        return toAjax(infoService.updateInfo(info));
    }

    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "资讯管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds) {
        return toAjax(infoService.deleteInfoByIds(infoIds));
    }
}
