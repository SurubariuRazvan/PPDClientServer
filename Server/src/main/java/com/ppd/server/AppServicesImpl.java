package com.ppd.server;

import com.ppd.domain.Currency;
import com.ppd.domain.Sale;
import com.ppd.domain.Show;
import com.ppd.domain.ShowRoom;
import com.ppd.repository.ShowRepository;
import com.ppd.repository.ShowRoomRepository;
import com.ppd.service.IAppObserver;
import com.ppd.service.IAppServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppServicesImpl implements IAppServices {
    private final Set<IAppObserver> loggedUsers;
    private final ShowRepository showRepository;
    private final ShowRoomRepository showRoomRepository;

    public AppServicesImpl(ShowRepository showRepository, ShowRoomRepository showRoomRepository) {
        this.showRepository = showRepository;
        this.showRoomRepository = showRoomRepository;
        this.loggedUsers = new HashSet<>();
        addData();
    }

    private void addData() {
        ShowRoom showRoom1 = new ShowRoom(0L, 100, new ArrayList<>());
        showRoom1.getShows().add(new Show(0, LocalDateTime.now().withHour(10).withMinute(0).withSecond(0).withNano(0).plusDays(0),
                                          "Show 1", new Currency(100, "RON"), new ArrayList<>(), showRoom1));
        showRoom1.getShows().add(new Show(0, LocalDateTime.now().withHour(14).withMinute(0).withSecond(0).withNano(0).plusDays(1),
                                          "Show 2", new Currency(200, "RON"), new ArrayList<>(), showRoom1));
        showRoom1.getShows().add(new Show(0, LocalDateTime.now().withHour(18).withMinute(0).withSecond(0).withNano(0).plusDays(2),
                                          "Show 3", new Currency(150, "RON"), new ArrayList<>(), showRoom1));
        showRoomRepository.save(showRoom1);
    }

    @Override
    public synchronized void login(IAppObserver client) {
        loggedUsers.add(client);
    }

    @Override
    public boolean buyTickets(Set<Integer> seats) {
        ShowRoom showRoom = showRoomRepository.findAll().get(0);
        Show show = showRoom.getShows().get(0);
        if (show.getSales().stream().allMatch(sale -> Collections.disjoint(sale.getSoldSeats(), seats))) {
            show.getSales().add(new Sale(0, show, seats, LocalDateTime.now()));
            showRepository.save(show);
            return true;
        } else {
            return false;
        }
    }
}