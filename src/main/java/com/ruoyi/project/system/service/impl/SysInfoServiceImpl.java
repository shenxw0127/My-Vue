package com.ruoyi.project.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysInfo;
import com.ruoyi.project.system.mapper.SysInfoMapper;
import com.ruoyi.project.system.service.ISysInfoService;

/**
 * 资讯信息 服务层处理
 */
@Service
public class SysInfoServiceImpl implements ISysInfoService {
    @Autowired
    private SysInfoMapper infoMapper;

    /**
     * 查询资讯信息集合
     *
     * @param info 资讯信息
     * @return 资讯信息集合
     */
    @Override
    public List<SysInfo> selectInfoList(SysInfo info) {
//        return userMapper.selectUserList(user);
        //如果当前用户是admin，那么就查询所有用户
        if (SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            //打印“是管理员”
            System.out.println("是管理员");
            return infoMapper.selectInfoList(info);
        } else {
            //如果当前用户不是admin，那么就查询当前用户创建的用户和当前用户
            System.out.println("不是管理员");

            String userName = SecurityUtils.getUsername();
            List<SysInfo> sysInfos = infoMapper.selectInfoListByUserName(SecurityUtils.getUsername());
            return sysInfos;
        }
    }

    /**
     * 通过资讯ID查询资讯信息
     *
     * @param infoId 资讯ID
     * @return 资讯对象信息
     */
    @Override
    public SysInfo selectInfoById(Long infoId) {
        return infoMapper.selectInfoById(infoId);
    }

    /**
     * 校验资讯标题是否唯一
     *
     * @param info 资讯信息
     * @return 结果
     */
    @Override
    public boolean checkInfoTitleUnique(SysInfo info) {
        Long infoId = StringUtils.isNull(info.getInfoId()) ? -1L : info.getInfoId();
        SysInfo uniqueInfo = infoMapper.checkInfoTitleUnique(info.getTitle());
        if (StringUtils.isNotNull(uniqueInfo) && uniqueInfo.getInfoId().longValue() != infoId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 删除资讯信息
     *
     * @param infoId 资讯ID
     * @return 结果
     */
    @Override
    public int deleteInfoById(Long infoId) {
        return infoMapper.deleteInfoById(infoId);
    }

    /**
     * 批量删除资讯信息
     *
     * @param infoIds 需要删除的资讯ID
     * @return 结果
     */
    @Override
    public int deleteInfoByIds(Long[] infoIds) {
        return infoMapper.deleteInfoByIds(infoIds);
    }

    /**
     * 新增保存资讯信息
     *
     * @param info 资讯信息
     * @return 结果
     */
    @Override
    public int insertInfo(SysInfo info) {
        return infoMapper.insertInfo(info);
    }

    /**
     * 修改保存资讯信息
     *
     * @param info 资讯信息
     * @return 结果
     */
    @Override
    public int updateInfo(SysInfo info) {
        return infoMapper.updateInfo(info);
    }
}
