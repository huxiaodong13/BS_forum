package com.jason.util;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
public class JspFilter implements Filter {  
      
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
        String url = httpServletRequest.getRequestURI();  
        
        String header = httpServletRequest.getHeader("Referer");
        
        if(header == null && url != null && url.endsWith(".jsp") && !url.contains("index.jsp")) {  
            httpServletResponse.sendRedirect("index.jsp");  
            return;
        }  
        chain.doFilter(request, response);  
    }  
  
    @Override  
    public void destroy() {  
          
    }  
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  
  
}