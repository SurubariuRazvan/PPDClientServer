package com.ppd.service;

import java.util.Set;

public interface IAppServices {

    void login(IAppObserver client);

    boolean buyTickets(Set<Integer> seats);
}
