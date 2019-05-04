package com.example.mylibrary.service;

import com.example.mylibrary.dao.TicketDAO;
import com.example.mylibrary.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    public void addTicket(Ticket ticket) {
        ticketDAO.addTicket(ticket);
    }

    public Ticket getTicket(int id) {
        return ticketDAO.selectTicketById(id);
    }

    public Ticket getTicket(String string) {
        return ticketDAO.selectTicket(string);
    }

    public void deleteTicket(int tid){
        ticketDAO.deleteTicketById(tid);
    }

    public void deleteTicket(String t){
        ticketDAO.deleteTicket(t);
    }

}
