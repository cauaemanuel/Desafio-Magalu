package com.magalu.demo.service;

import com.magalu.demo.controller.dto.ScheduleNotificationDto;
import com.magalu.demo.entity.Notification;
import com.magalu.demo.entity.Status;
import com.magalu.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto){
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long id){
        return notificationRepository.findById(id);
    }

    public void cancellerNotification(Long id){
        var notification = notificationRepository.findById(id);

        if(notification.isPresent()){
            notification.get().setStatus(Status.Values.CANCELLER.toStatus());
            notificationRepository.save(notification.get());
        }
    }
}
