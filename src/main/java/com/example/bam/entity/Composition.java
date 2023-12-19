package com.example.bam.entity;

import lombok.*;

import javax.persistence.*;
import java.io.File;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "composition")
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private byte[] notes;

    @Column
    private byte[] song;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    @Column (columnDefinition = "text")
    private String description;

    @Column
    private String genre;
}





