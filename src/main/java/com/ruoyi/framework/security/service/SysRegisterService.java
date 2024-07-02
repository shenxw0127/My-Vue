package com.ruoyi.framework.security.service;

import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysTenant;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.system.service.ISysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.RegisterBody;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysConfigService;
import com.ruoyi.project.system.service.ISysUserService;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysTenantService sysTenantService;

    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        String email = registerBody.getEmail(), phone = registerBody.getPhoneNumber(),
                sex = registerBody.getSex(), remark = registerBody.getRemark();
        String tenantName = registerBody.getTenantName(), contactPerson = registerBody.getContactPerson();
        //打印
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("email: " + email);
        System.out.println("phone: " + phone);
        System.out.println("sex: " + sex);
        System.out.println("remark: " + remark);
        System.out.println("tenantName: " + tenantName);
        System.out.println("contactPerson: " + contactPerson);

        SysUser sysUser = new SysUser();
        SysTenant sysTenant = new SysTenant();
        SysDept sysDept = new SysDept();
        sysTenant.setTenantId((long) (Math.random() * 900000 + 100000));
        sysDept.setDeptId(sysTenant.getTenantId());

        sysUser.setUserName(username);

        // 验证码开关
//        boolean captchaEnabled = configService.selectCaptchaEnabled();
//        if (captchaEnabled)
//        {
//            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
//        }

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (!userService.checkUserNameUnique(sysUser))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            sysUser.setPhonenumber(phone);
            sysUser.setRemark(remark);
            sysUser.setEmail(email);
            sysUser.setSex(sex);
            sysUser.setNickName("租户管理员");
            sysUser.setDeptId(sysTenant.getTenantId());
            sysUser.setRoleIds(new Long[]{100L});
            boolean regFlag = userService.registerUser(sysUser);

            sysTenant.setAdmin(username);
            sysTenant.setContactPerson(contactPerson);
            sysTenant.setPhoneNumber(phone);
            sysTenant.setTenantName(tenantName);
            sysTenantService.insertTenant(sysTenant);

            sysDept.setPhone(phone);
            sysDept.setDeptName(tenantName);
            sysDept.setLeader(contactPerson);
            sysDept.setParentId(100L);
            sysDept.setEmail(email);
            sysDeptService.insertDept(sysDept);

//            msg = "注册失败,请联系系统管理人员";
//
//            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
