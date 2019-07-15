package com.qf.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;


/**
 * @autor hhh
 * @create 2019/7/12
 */
public class TestAuthorization {
    public static void main(String[] args) {
        Factory<SecurityManager> factory  = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin2","1234");
        try{
            subject.login(usernamePasswordToken);
            if(subject.isAuthenticated()){
                System.out.println("登录成功");
//        编程式
//                isPermitted判断用户是否具有某个权限
                System.out.println(subject.isPermitted("user:update"));
////              判断用户是否具有某些权限
//                boolean[] ll =subject.isPermitted("user:select","user:update");
//                for (boolean b:ll){
//                    System.out.println(b);
//                }
//                checkPermission和isPermitted的区别:
//                当检测账户没有某个权限时。ispermitted返回false，checkPermission抛出异常
//                subject.checkPermission("user:delete");
//                判断账户具有的角色hasrole
//                同之前相似一个抛出异常，一个返回false checkRole和hasRole的区别同上
                System.out.println(subject.hasRole("role1"));
                subject.checkRole("role1");


            }

        }catch(AuthenticationException a){
            System.out.println("登录失败");
        }


    }
}
