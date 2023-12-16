package com.ucheikenna.musalasoftdroneproject.pojo.requests;

import com.ucheikenna.musalasoftdroneproject.enums.Model;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneRequest {

    private Model model;
}
