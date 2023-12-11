package com.example.kameleoontrialtask.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "quotes")
@EqualsAndHashCode
public class Quote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rating;
    private String content;
    private LocalDateTime expirationDate;
    private LocalDateTime updatingDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @Transient
    private List<Vote> votes;
}
