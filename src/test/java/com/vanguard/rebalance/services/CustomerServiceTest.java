package com.vanguard.rebalance.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.vanguard.rebalance.model.Customer;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceTest {

  @Autowired
  private CustomerService customerService;

  @Test
  public void testLoadCustomers() {
    Optional<List<Customer>> customers = customerService.loadCustomers();

    assertThat(customers.isEmpty()).isFalse();
  }

}