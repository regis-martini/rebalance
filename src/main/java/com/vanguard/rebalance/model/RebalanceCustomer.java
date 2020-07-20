package com.vanguard.rebalance.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;

@Builder
public class RebalanceCustomer {

  private long customerId;
  private String email;
  private String dateOfBirth;
  private int riskLevel;
  private int retirementAge;
  private double stocksPercentage;
  private double cashPercentage;
  private double bondsPercentage;
}
