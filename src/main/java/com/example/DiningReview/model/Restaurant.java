package com.example.DiningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull private String name;
    @NonNull private String cuisine;
    private Integer peanutScore = 0;
    private Integer eggScore = 0;
    private Integer dairyScore = 0;
    private Double totalScore = 0.0;
}
