package com.example.PORTClaimApp.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotification;

    @Column(length = 1000)
    private String contenu;

    private Boolean read;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateHorloge;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;
}
