package com.bupt.proj.service;

import com.bupt.proj.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;
import com.bupt.proj.model.User;

@Service

public class HostService {
    public User getUser(){return ConcurrentUtils.getHost();
    }
    public void setUser(User user){ConcurrentUtils.setHost(user);}
}
