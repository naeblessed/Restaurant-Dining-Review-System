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
public class DiningReview {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull private String whoSubmitted;
    @NonNull private String restaurant;
    private Integer peanutScore = null;
    private Integer eggScore = null;
    private Integer dairyScore = null;
    private String commentary = null;
    private ReviewStatus reviewStatus = null;
}

