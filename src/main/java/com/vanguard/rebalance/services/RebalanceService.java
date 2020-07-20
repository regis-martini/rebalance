package com.vanguard.rebalance.services;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import com.vanguard.rebalance.model.Customer;
import com.vanguard.rebalance.model.RebalanceCustomer;
import com.vanguard.rebalance.model.Strategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RebalanceService {

  @Autowired
  private CustomerService customerService;
  @Autowired
  private StrategyService strategyService;

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  public void rebalancePortfolio() {
    Map<Optional<List<Integer>>, List<RebalanceCustomer>> sortCustomersToRebalance = sortCustomersToRebalance();
    sendRebalanceRequest(sortCustomersToRebalance);

  }

  /**
   * The method sorts customers that must be rebalanced and customers that don't have a matching
   * strategy The result is organized in Map . The key is the strategy range and the value is a list
   * of customers that were matched to that strategy
   *
   * @return Map
   */
  public Map<Optional<List<Integer>>, List<RebalanceCustomer>> sortCustomersToRebalance() {
    Optional<List<Customer>> optCustomers = customerService.loadCustomers();
    Optional<List<Strategy>> optStrategies = strategyService.loadStrategies();

    Map<List<Integer>, Strategy> strategies = new HashMap<>();
    optStrategies
        .orElseThrow(
            () -> new IllegalStateException("Houston we have a problem -> missing strategies"))
        .forEach(strategy -> {
          strategies.putIfAbsent(IntStream
              .rangeClosed(strategy.getMinRiskLevel(), strategy.getMaxRiskLevel()).boxed()
              .collect(toList()), strategy);
        });

    Map<Optional<List<Integer>>, List<Customer>> sortedCustomers = optCustomers
        .orElseThrow(
            () -> new IllegalStateException("Houston we have a problem -> missing customers"))
        .stream()
        .collect(groupingBy(
            customer -> strategies.keySet().stream()
                .filter(list -> list.contains(customer.getRiskLevel()))
                .findFirst()));

    Map<Optional<List<Integer>>, List<RebalanceCustomer>> enrichedSortCustomers = new HashMap<>();
    sortedCustomers.entrySet()
        .forEach(optionalListEntry -> enrichedSortCustomers.put(optionalListEntry.getKey(),
            getRebalanceCustomer(optionalListEntry.getValue(),
                strategies.get(optionalListEntry.getKey()))));

    return enrichedSortCustomers;
  }

  private List<RebalanceCustomer> getRebalanceCustomer(List<Customer> customers,
      Strategy strategy) {
    if (strategy == null) {
      return customers.stream()
          .map(cus -> RebalanceCustomer.builder()
              .customerId(cus.getCustomerId())
              .dateOfBirth(cus.getDateOfBirth())
              .retirementAge(cus.getRetirementAge())
              .email(cus.getEmail())
              .cashPercentage(100)
              .build())
          .collect(Collectors.toList());
    }
    return customers.stream()
        .map(cus -> RebalanceCustomer.builder()
            .customerId(cus.getCustomerId())
            .dateOfBirth(cus.getDateOfBirth())
            .retirementAge(cus.getRetirementAge())
            .email(cus.getEmail())
            .bondsPercentage(strategy.getBondsPercentage())
            .stocksPercentage(strategy.getStocksPercentage())
            .cashPercentage(strategy.getCashPercentage())
            .build())
        .collect(Collectors.toList());
  }


  private void requestRebalance(List<RebalanceCustomer> customers) {
    //call the Financial Portfolio Service (FPS) to rebalance customers
  }

  public void sendRebalanceRequest(
      Map<Optional<List<Integer>>, List<RebalanceCustomer>> customers) {
    customers.entrySet().stream()
        .filter(map -> map.getKey().isPresent())
        .forEach(map -> requestRebalance(map.getValue()));
  }

}
