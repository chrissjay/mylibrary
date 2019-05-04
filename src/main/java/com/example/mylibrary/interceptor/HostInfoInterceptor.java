package com.example.mylibrary.interceptor;

import com.example.mylibrary.model.Ticket;
import com.example.mylibrary.model.User;
import com.example.mylibrary.service.TicketService;
import com.example.mylibrary.service.UserService;
import com.example.mylibrary.utils.ConcurrentUtils;
import com.example.mylibrary.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class HostInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    /**
     * 为注入host信息
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String t = CookieUtils.getCookie("t", request);
        if (!StringUtils.isEmpty(t)) {
            Ticket ticket = ticketService.getTicket(t);
            if (ticket != null && ticket.getExpiredAt().after(new Date())) {
                User host = userService.getUser(ticket.getUserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }

}
