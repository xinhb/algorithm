package com.imooc.passbook.security;

import com.imooc.passbook.constant.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>权限拦截器</h1>
 * Created by Qinyi.
 */
@Component
public class AuthCheckInterceptor implements HandlerInterceptor {

    /**
     * 实现原生拦截器，重写它的前向拦截器方法，过滤掉不带token或者token不对的http请求
     * */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {


        String token = httpServletRequest.getHeader(Constants.TOKEN_STRING);

        //工具类方法判断这个http请求是否带有token(判断为null或者长度为零)
        if (StringUtils.isEmpty(token)) {
            throw new Exception("Header 中缺少 " + Constants.TOKEN_STRING + "!");
        }
        //判断token是否相同
        if (!token.equals(Constants.TOKEN)) {
            throw new Exception("Header 中 " + Constants.TOKEN_STRING + "错误!");
        }
        //token 与之前记录相同，存下来（且每个线程单独一份）
        AccessContext.setToken(token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        AccessContext.clearAccessKey();
    }
}
