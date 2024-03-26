package org.pronet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Date publishedDate;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @PrePersist
    protected void onCreate() {
        this.updatedAt = null;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}