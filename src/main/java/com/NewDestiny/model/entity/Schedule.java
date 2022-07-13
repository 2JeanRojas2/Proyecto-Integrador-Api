package com.NewDestiny.model.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bookingStartTime")
    private LocalTime startTime;
    @Column(name = "scheduleStartDate")
    private LocalDate startDate;
    @Column(name = "scheduleEndDate")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "products_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private UserEntity userEntity;
}

