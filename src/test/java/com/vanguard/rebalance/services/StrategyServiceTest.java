package com.vanguard.rebalance.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.vanguard.rebalance.model.Customer;
import com.vanguard.rebalance.model.Strategy;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StrategyServiceTest {

  @Autowired
  private StrategyService strategyService;

  @Test
  public void testLoadStrategy() {
    Optional<List<Strategy>> strategies = strategyService.loadStrategies();

    assertThat(strategies.isEmpty()).isFalse();
  }

}