package com.bupt.proj.service;

import com.bupt.proj.dao.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bupt.proj.model.Ticket;
@Service
public class TicketService {
    @Autowired
    private TicketDAO ticketDAO;
    public void addTicket(Ticket t){ ticketDAO.addTicket(t); }
    public Ticket getTicket(int vid) { return ticketDAO.selectByUserId(vid);}
    public Ticket getTicket(String t) { return ticketDAO.selectByTicket(t);}
    public void deleteTicket(int tid){
        ticketDAO.deleteTicketById(tid);
    }
    public void deleteTicket(String t){
        ticketDAO.deleteTicket(t);
    }
}
