package org.sid.ebankingbackend.dtos;

import lombok.Data;

@Data 
public class TransferDTO {

    private String accounSource;
    private String accounDestination;
    private double amount;
    private String description;
}
