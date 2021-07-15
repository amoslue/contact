package com.bupt.proj.biz;
//import com.ibm.wsdl.util.StringUtils;
import com.bupt.proj.service.UserService;
import com.bupt.proj.utils.ConcurrentUtils;
import com.bupt.proj.utils.MD5;
import com.bupt.proj.utils.TicketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.bupt.proj.model.Ticket;
import com.bupt.proj.model.User;
import com.bupt.proj.model.execpions.LoginRegisterException;
import com.bupt.proj.service.TicketService;
//import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Service
public class LoginBiz {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    public String login(String email,String password) throws Exception {
        User user = userService.getUser(email);

        if (user==null){
            throw new LoginRegisterException("用户不存在");
        }
        if (!StringUtils.pathEquals(MD5.next(password),user.getPassword()))
            throw new LoginRegisterException("密码错误");

        Ticket t=ticketService.getTicket(user.getId());
        if (t==null) {
            t= TicketUtils.next(user.getId());
            ticketService.addTicket(t);
            return t.getTicket();

        }
        
        if (t.getExpiredAt().before(new Date())){
            ticketService.deleteTicket(t.getId());
        }
        
        t=TicketUtils.next(user.getId());
        ticketService.addTicket(t);

        ConcurrentUtils.setHost(user);

        return t.getTicket();
        
        
    }
    
    public void logout (String t){ticketService.deleteTicket(t);}
    public String register (User user) throws Exception {
        if (userService.getUser(user.getPhone())!=null)
            throw new LoginRegisterException("用户已存在！");

        String plain =user.getPassword();
        String md5=MD5.next(plain);
        user.setPassword(md5);
        userService.addUser(user);

        Ticket ticket =TicketUtils.next(user.getId());
        ticketService.addTicket(ticket);

        ConcurrentUtils.setHost(user);
        return ticket.getTicket();

    }

}
