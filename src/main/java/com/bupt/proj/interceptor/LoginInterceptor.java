package com.bupt.proj.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.bupt.proj.model.Ticket;
import com.bupt.proj.service.TicketService;
import com.bupt.proj.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketService ticketService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception
    {
        String t= CookieUtils.getCookie("t",request);
      //没有ticket，去登陆！
        if(StringUtils.isEmpty(t)){
            response.sendRedirect("/users/login");
            return false;
        }

        Ticket ticket =ticketService.getTicket(t);

       //ticket无效，去重新登录！
        if (ticket==null){
            response.sendRedirect("/user/login");
            return false;
        }


        //ticket过期，去登录！
        if(ticket.getExpiredAt().before(new Date())){
            response.sendRedirect("/user/login");
            return false;
         }
        return true;
    }


}
