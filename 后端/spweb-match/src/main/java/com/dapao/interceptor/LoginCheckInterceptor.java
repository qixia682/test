package com.dapao.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dapao.pojo.Result;
import com.dapao.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行 返回为true放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取url
        String url = request.getRequestURL().toString();
        log.info("url:{}", url);

        //检查是否是访问登录界面
        if (url.contains("login")) {
            log.info("登录操作，直接放行");
            return true;
        }
        //检查jwt是否存在
        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            log.info("jwt不存在");
            Result error = Result.error("NOT_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return false;
        }
        //检查jwt合法性
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) { //解析失败
            e.printStackTrace();
            log.info("jwt解析失败");
            Result error = Result.error("NOT_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            response.getWriter().write(noLogin);
            return false;
        }
        //都没问题，放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override //目标资源运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override//渲染结束后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
