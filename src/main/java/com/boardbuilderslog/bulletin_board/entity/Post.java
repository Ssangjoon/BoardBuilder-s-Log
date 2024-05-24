package com.boardbuilderslog.bulletin_board.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private boolean isPublic;
    private String thumbnailUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
