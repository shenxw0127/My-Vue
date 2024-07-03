package com.ruoyi.project.system.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysMeeting;
import com.ruoyi.project.system.service.ISysMeetingService;

/**
 * 会议信息操作处理
 */
@RestController
@RequestMapping("/system/meeting")
public class SysMeetingController extends BaseController {
    @Autowired
    private ISysMeetingService meetingService;

    /**
     * 获取会议列表
     */
    @PreAuthorize("@ss.hasPermi('system:meeting:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMeeting meeting) {
        startPage();
        List<SysMeeting> list = meetingService.selectMeetingList(meeting);
        return getDataTable(list);
    }

    /**
     * 导出会议列表
     */
    @Log(title = "会议管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:meeting:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMeeting meeting) {
        List<SysMeeting> list = meetingService.selectMeetingList(meeting);
        ExcelUtil<SysMeeting> util = new ExcelUtil<>(SysMeeting.class);
        util.exportExcel(response, list, "会议数据");
    }

    /**
     * 根据会议编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:meeting:query')")
    @GetMapping(value = "/{meetingId}")
    public AjaxResult getInfo(@PathVariable Long meetingId) {
        return success(meetingService.selectMeetingById(meetingId));
    }

    /**
     * 新增会议
     */
    @PreAuthorize("@ss.hasPermi('system:meeting:add')")
    @Log(title = "会议管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMeeting meeting) {
        meeting.setCreateBy(getUsername());
        return toAjax(meetingService.insertMeeting(meeting));
    }

    /**
     * 修改会议
     */
    @PreAuthorize("@ss.hasPermi('system:meeting:edit')")
    @Log(title = "会议管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMeeting meeting) {
        meeting.setUpdateBy(getUsername());
        return toAjax(meetingService.updateMeeting(meeting));
    }

    /**
     * 删除会议
     */
    @PreAuthorize("@ss.hasPermi('system:meeting:remove')")
    @Log(title = "会议管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{meetingIds}")
    public AjaxResult remove(@PathVariable Long[] meetingIds) {
        return toAjax(meetingService.deleteMeetingByIds(meetingIds));
    }

    /**
     * 上传会议封面
     */
    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String filePath = RuoYiConfig.getProfile() + "/upload";
            String fileName = FileUploadUtils.upload(filePath, file);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", "/upload/" + fileName);
            return ajax;
        }
        return AjaxResult.error("上传文件异常，请联系管理员");
    }
}