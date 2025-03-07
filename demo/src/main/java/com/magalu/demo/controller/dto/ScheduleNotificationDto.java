package com.magalu.demo.controller.dto;

import com.magalu.demo.entity.Channel;
import com.magalu.demo.entity.Notification;
import com.magalu.demo.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime dateTime, String destination, String message, Channel.Values channel) {

    public Notification toNotification(){
        return new Notification(
                dateTime,
                destination,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()
        );
    }
}

