package com.bupt.proj.utils;

import org.joda.time.DateTime;
import com.bupt.proj.model.Ticket;

public class TicketUtils {
    public static Ticket next(int vid){
        Ticket ticket =new Ticket();
        ticket.setTicket(UuidUtils.next());
        ticket.setUserId(vid);
        DateTime expiredTime = new DateTime();
        expiredTime = expiredTime.plusMonths(2);
        ticket.setExpiredAt(expiredTime.toDate());
        return ticket;


    }

}
