package org.sid.ebankingbackend.repositories;

import java.util.List;

import org.sid.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    //public List<Customer> findByNameContains(String key);

    @Query ("select c from Customer c where c.name like :kw")
    public List<Customer> findByNameContains(@Param(value="kw") String keyword);
}   

