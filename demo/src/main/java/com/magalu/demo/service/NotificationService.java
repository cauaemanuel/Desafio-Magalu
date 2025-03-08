package com.magalu.demo.service;

import com.magalu.demo.controller.dto.ScheduleNotificationDto;
import com.magalu.demo.entity.Notification;
import com.magalu.demo.entity.Status;
import com.magalu.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    public void checkAndSend(LocalDateTime dateTime){
        var notificatons = notificationRepository.findByStatusInAndDateTimeBefore(List.of(
                Status.Values.PENDING.toStatus(),
                Status.Values.ERROR.toStatus()
        ), dateTime);

        notificatons.forEach(sendNotification());
    }

    @Transactional
    private Consumer<Notification> sendNotification() {
        return n -> {

            //TODO Realiza a logica der envio

            n.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(n);

        };
    }
}
