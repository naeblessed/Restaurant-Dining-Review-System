package com.example.DiningReview.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="TACO_USER")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    @NonNull private String username;
    @NonNull private String name;
    @NonNull private String city;
    @NonNull private String state;
    @NonNull private Integer zipcode;
    @NonNull private Boolean peanutAllergies;
    @NonNull private Boolean eggAllergies;
    @NonNull private Boolean dairyAllergies;
}
