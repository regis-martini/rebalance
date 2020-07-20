package com.vanguard.rebalance.services;

import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.vanguard.rebalance.model.RebalanceCustomer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RebalanceServiceTest {

  @Autowired
  private RebalanceService rebalanceService;

  @Test
  public void testSortCustomersToRebalance() {
    Map<Optional<List<Integer>>, List<RebalanceCustomer>> sortCustomersToRebalance = rebalanceService
        .sortCustomersToRebalance();

    assertThat(sortCustomersToRebalance).isNotNull();
    assertThat(sortCustomersToRebalance.get(empty())).isNull();
    Optional<Entry<Optional<List<Integer>>, List<RebalanceCustomer>>> entry = sortCustomersToRebalance
        .entrySet().stream().findFirst();
    assertThat(entry).isNotEmpty();
    assertThat(entry.get().getValue()).isNotEmpty();
  }
}