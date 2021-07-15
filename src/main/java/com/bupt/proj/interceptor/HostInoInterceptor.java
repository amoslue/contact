package com.bupt.proj.interceptor;

//import org.apache.cxf.common.util.StringUtils;
import com.bupt.proj.service.UserService;
import com.bupt.proj.utils.ConcurrentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.bupt.proj.model.Ticket;
import com.bupt.proj.model.User;
import com.bupt.proj.service.TicketService;
import com.bupt.proj.utils.CookieUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@Component
public class HostInoInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)
        throws Exception{
        String t= CookieUtils.getCookie("t",request);
        if(!StringUtils.isEmpty(t)){
            Ticket ticket =ticketService.getTicket(t);
            if (ticket!=null&&ticket.getExpiredAt().after(new Date())){
                User host=userService.getUser(ticket.getUserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }
}
