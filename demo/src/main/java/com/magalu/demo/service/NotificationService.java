package com.magalu.demo.service;

import com.magalu.demo.controller.dto.ScheduleNotificationDto;
import com.magalu.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto){

        notificationRepository.save(dto.toNotification());
    }
}
