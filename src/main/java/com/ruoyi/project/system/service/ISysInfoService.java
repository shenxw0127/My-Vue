package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.SysInfo;

/**
 * 资讯信息 服务层
 */
public interface ISysInfoService {
    /**
     * 查询资讯信息集合
     *
     * @param info 资讯信息
     * @return 资讯列表
     */
    public List<SysInfo> selectInfoList(SysInfo info);

    /**
     * 通过资讯ID查询资讯信息
     *
     * @param infoId 资讯ID
     * @return 资讯对象信息
     */
    public SysInfo selectInfoById(Long infoId);

    /**
     * 校验资讯标题是否唯一
     *
     * @param info 资讯信息
     * @return 结果
     */
    public boolean checkInfoTitleUnique(SysInfo info);

    /**
     * 删除资讯信息
     *
     * @param infoId 资讯ID
     * @return 结果
     */
    public int deleteInfoById(Long infoId);

    /**
     * 批量删除资讯信息
     *
     * @param infoIds 需要删除的资讯ID
     * @return 结果
     */
    public int deleteInfoByIds(Long[] infoIds);

    /**
     * 新增保存资讯信息
     *
     * @param info 资讯信息
     * @return 结果
     */
    public int insertInfo(SysInfo info);

    /**
     * 修改保存资讯信息
     *
     * @param info 资讯信息
     * @return 结果
     */
    public int updateInfo(SysInfo info);
}
