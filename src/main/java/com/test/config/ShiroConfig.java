package com.test.config;

import com.test.dao.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(Realm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    /**
     * 配置一个自定义的Realm的bean，最终将使用这个bean返回的对象来完成授权和认证
     */
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        // 创建shiro的拦截器，用于拦截用户请求
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();

        // 设置shiro的安全管理，设置管理的同时也会指定某个Realm用来完成权限分配
        shiroFilter.setSecurityManager(securityManager);

        // 用于设置一个登陆前的请求地址，这个地址可以是一个html或jsp的访问路径，也可以是一个controller的路径
        // 作用是用于通知shiro可以使用这里路径跳转到登录页面，但shiro判断我们当前的用户没有登陆时就会自动跳转到这个路径要求用户完成登录
        // 参数代表登录路径，根据实际修改
        shiroFilter.setLoginUrl("/");

        // 登录成功后跳转的页面，由于用户的等于后期需要交给shiro完成，因此需要通知shiro登陆成功之后返回到哪个位置
        shiroFilter.setSuccessUrl("/success");

        // 指定没有权限的页面，当用户访问某个功能时如果shiro判断用户没有对应的操作权限，那么shiro就会将请求转向到这个controller，
        // 用户提醒用户没有操作权限
        shiroFilter.setUnauthorizedUrl("/noPermission");

        // 定义一个map，存放的数据全部都是规则，用于设置通知shiro什么样的请求可以访问，什么样的请求不可以访问
        Map<String,String> filterChainMap = new LinkedHashMap<>();
        // login表示请求的路径，anon表示可以使用游客进行登录(这个请求不需要登录)
        filterChainMap.put("/login","anon");
        /**
         * 配置权限规则，也可以存在数据库
         * 也可以在controller中使用注解
         * admin/** 表示一个请求名字的统配，以admin开头的任意子路径下的所有请求
         * authc 表示这个请求需要进行认证，只有认证通过才能访问
         * ** 表示任意子路径
         * * 表示任意的一个路径
         * ? 表示任意的一个字符
         */
        filterChainMap.put("/admin/**","authc");
        filterChainMap.put("/user/**","authc");
        // 表示所有的请求路径都需要被拦截登录，这个必须卸载map集合的最后面，这个选项是可选的
        // 如果没有指定/** 那么如果前面配的规则都不符合，那么shiro将放行这个请求
        //filterChainMap.put("/**","authc");

        shiroFilter.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilter;
    }

}
