package com.ppd.controller;

import com.ppd.service.IAppObserver;
import com.ppd.service.IAppServices;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

public class Controller extends UnicastRemoteObject implements IAppObserver, Serializable {

    private IAppServices service;

    public Controller() throws RemoteException {
    }

    public void setService(IAppServices service) {
        this.service = service;
        login();
        makeRequests();
    }

    private void makeRequests() {
        Set<Integer> seats = new HashSet<>();
        seats.add(3);
        seats.add(4);
        System.out.println(service.buyTickets(seats));
    }

    public void login() {
        service.login(this);
    }
}
