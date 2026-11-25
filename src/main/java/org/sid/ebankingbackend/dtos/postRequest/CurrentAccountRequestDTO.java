package org.sid.ebankingbackend.dtos.postRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
public class CurrentAccountRequestDTO {

    private double initialBalance;
    private Long customerId;
    private double overDraft;

   
}
