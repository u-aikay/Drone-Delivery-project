package com.ucheikenna.musalasoftdroneproject.pojo.requests;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationRequest {

    private String name;
    private Double weight;
    private String image;
}
