package com.demo.angularpractice.account.filter
        ;

import com.demo.angularpractice.account.service.manager.AccountAccessDecisionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

/**
 * security过滤器
 *
 * @author dzy
 */
@Service
public class SecurityInterceptorFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public void setMyAccessDecisionManager(AccountAccessDecisionManager accountAccessDecisionManager) {
        super.setAccessDecisionManager(accountAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 调用AccountInvocationSecurityMetadataSourceService的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
     *
     * @param chain 包含被拦截的url
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        //InterceptorStatusToken token = beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
           // super.afterInvocation(token, null);
        }
    }

    @Override
    protected InterceptorStatusToken beforeInvocation(Object object) {
        return super.beforeInvocation(object);
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
