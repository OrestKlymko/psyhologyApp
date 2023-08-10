package com.psychology.web.customer.repo;

import com.psychology.web.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
	public CustomerEntity getCustomerEntityByEmail(String email);
}
