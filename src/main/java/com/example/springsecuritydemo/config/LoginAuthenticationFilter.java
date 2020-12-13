package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.bean.LoginBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginBean loginBean = null;
        ObjectMapper mapper = new ObjectMapper();
        UsernamePasswordAuthenticationToken authRequest = null;
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            try (InputStream stream = request.getInputStream()) {
                Map<String, String> body = mapper.readValue(stream, Map.class);
                if(body.get("account") == null || body.get("password") == null){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    Map<String, Object> data = new HashMap<>();
                    data.put("errorMsg", "登入失敗，請確認帳號或密碼");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(mapper.writeValueAsString(data));
                    throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.badCredentials");
                }
                authRequest = new UsernamePasswordAuthenticationToken(body.get("account"), body.get("password"));
            } catch (IOException e) {
                authRequest = new UsernamePasswordAuthenticationToken("", "");
            }
        }else {
            throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.badCredentials");
        }

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
