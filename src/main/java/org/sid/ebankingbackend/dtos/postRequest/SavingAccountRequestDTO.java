package org.sid.ebankingbackend.dtos.postRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
public class SavingAccountRequestDTO {

    private double initialBalance;
    private Long customerId;

   
}
