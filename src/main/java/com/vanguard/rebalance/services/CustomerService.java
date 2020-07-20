package com.vanguard.rebalance.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.vanguard.rebalance.model.Customer;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerService {

  public Optional<List<Customer>> loadCustomers() {

    InputStream inputStream = getClass()
        .getClassLoader().getResourceAsStream("customer.csv");

    try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      CsvToBean<Customer> csvToBean = new CsvToBeanBuilder(reader)
          .withType(Customer.class)
          .withIgnoreLeadingWhiteSpace(true)
          .build();

      return Optional.of(csvToBean.parse());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return Optional.empty();
  }

}
