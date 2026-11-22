package org.sid.ebankingbackend.web;

import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.sid.ebankingbackend.dtos.AccountHistoryDTO;
import org.sid.ebankingbackend.dtos.BankAccountDTO;
import org.sid.ebankingbackend.dtos.CreditDTO;
import org.sid.ebankingbackend.dtos.DebitDTO;
import org.sid.ebankingbackend.dtos.OperationDTO;
import org.sid.ebankingbackend.dtos.SavingAccountDTO;
import org.sid.ebankingbackend.dtos.TransferDTO;
import org.sid.ebankingbackend.dtos.postRequest.SavingAccountRequestDTO;
import org.sid.ebankingbackend.exceptions.BalanceNotSufficientException;
import org.sid.ebankingbackend.exceptions.BankAccountNotFoundException;
import org.sid.ebankingbackend.exceptions.CustomerNotFoundException;
import org.sid.ebankingbackend.services.BankAccountService;
import org.sid.ebankingbackend.services.BankAccountServiceImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@RestController
@Data @AllArgsConstructor  
@CrossOrigin ("*")
public class BankAccountRestAPI {

    private BankAccountService bankAccountServiceImp ;


    @GetMapping ("/accounts/{id}")
    public BankAccountDTO getAccount(@PathVariable (name = "id") String accountId) throws BankAccountNotFoundException{

        BankAccountDTO account = bankAccountServiceImp.getAccount(accountId);
        return account;
    }

    @GetMapping ("/accounts")
    public List<BankAccountDTO> getAccounts(){

        List<BankAccountDTO> accounts = bankAccountServiceImp.bankAccountList();
        return accounts;
    }



    @GetMapping ("/accounts/{accountId}/operations")
    public List<OperationDTO> getAccountHistory(@PathVariable String accountId){

        List<OperationDTO> list = bankAccountServiceImp.accountHistory(accountId);

        return list;
    }

    @GetMapping ("/accounts/{accountId}/operationsPage")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                                @RequestParam (name = "page", defaultValue = "0") int page,
                                                @RequestParam (name = "size", defaultValue = "3") int size   ) throws BankAccountNotFoundException{

        AccountHistoryDTO res = bankAccountServiceImp.accountHistoryPage(accountId, page, size);

        return res;
    }




    @PostMapping ("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException{

        bankAccountServiceImp.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());

        return debitDTO;
    }

    @PostMapping ("/accounts/credit")
    public CreditDTO credit(@RequestBody  CreditDTO creditDTO) throws BankAccountNotFoundException, BalanceNotSufficientException{

        bankAccountServiceImp.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());

        return creditDTO;
    }


    @PostMapping ("/accounts/transfer")
    public void transfer(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException{

        bankAccountServiceImp.transfer(transferDTO.getAccounSource(), transferDTO.getAccounDestination(), transferDTO.getAmount());

    }

    @GetMapping ("/accounts/customer/{customerId}")
    public List<BankAccountDTO> customerAccounts(@PathVariable (name = "customerId") String customerId){

        List<BankAccountDTO> res = bankAccountServiceImp.bankAccountListByCustomerId(customerId);
        return  res;

    }


    @PostMapping ("/accounts/addSavingAccount")
    public SavingAccountDTO addSavingAccount(@RequestBody SavingAccountRequestDTO body) throws CustomerNotFoundException{

        double initialBalance = body.getInitialBalance();
        Long customerId = body.getCustomerId();

        return bankAccountServiceImp.saveSavingAccount(initialBalance,0, customerId);

    }







    


}
