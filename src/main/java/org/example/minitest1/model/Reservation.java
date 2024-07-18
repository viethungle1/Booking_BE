package org.example.minitest1.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    String guestName;
    String guestIdNo;
    String guestPhone;
    String guestEmail;
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime endDate;
    Double price;
    Integer status;
    @ManyToOne(fetch = FetchType.EAGER)
    Room room;
}
