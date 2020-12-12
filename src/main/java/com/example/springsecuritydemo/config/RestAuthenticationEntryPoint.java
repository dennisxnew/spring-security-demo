package com.example.springsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 統一處理權限不足、Session過期問題
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map.put("errorMsg", "系統閒置時間過長，將導頁至登入頁。");
        map.put("appCode", "SP002");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(httpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write(mapper.writeValueAsString(map));

//        httpServletResponse.getOutputStream().println(mapper.writeValueAsString(map)); //此段中文會錯誤???
//        String error = mapper.writeValueAsString(map);
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setStatus(httpServletResponse.SC_UNAUTHORIZED);
//        PrintWriter writer = httpServletResponse.getWriter();
//        writer.write(error);
//        writer.flush();
//        writer.close();
    }
}
