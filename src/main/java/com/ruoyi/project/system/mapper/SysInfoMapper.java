package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.SysInfo;

/**
 * 资讯信息 数据层
 */
public interface SysInfoMapper {
    /**
     * 查询资讯数据集合
     *
     * @param info 资讯信息
     * @return 资讯数据集合
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
     * 修改资讯信息
     *
     * @param info 资讯信息
     * @return 结果
     */
    public int updateInfo(SysInfo info);

    /**
     * 新增资讯信息
     *
     * @param info 资讯信息
     * @return 结果
     */
    public int insertInfo(SysInfo info);

    /**
     * 校验资讯标题
     *
     * @param title 资讯标题
     * @return 结果
     */
    public SysInfo checkInfoTitleUnique(String title);
}
