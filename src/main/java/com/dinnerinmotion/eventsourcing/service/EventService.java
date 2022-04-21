//package com.dinnerinmotion.eventsourcing.service;
//
//import com.dinnerinmotion.eventsourcing.entity.Event;
//import com.dinnerinmotion.eventsourcing.repository.EventRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class EventService {
//    @Autowired
//    private EventService eventService;
//    @Autowired
//    private EventRepository eventRepository;
//
//    public Event saveUser(Event event) {
//        log.info("inside save user method of UserService");
//        return eventRepository.save(event);
//    }
//
//    public User findUserById(Long userId) {
//        log.info("inside find user by id method of UserService");
//        return userRepository.findByUserId(userId);
//    }
//}
