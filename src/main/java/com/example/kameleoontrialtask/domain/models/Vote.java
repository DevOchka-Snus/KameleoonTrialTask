package com.example.kameleoontrialtask.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;
    private LocalDateTime expirationDate;

    public Vote(Status status, User author, Quote quote, LocalDateTime expirationDate) {
        this.status = status;
        this.author = author;
        this.quote = quote;
        this.expirationDate = expirationDate;
    }
}
