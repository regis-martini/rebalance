package com.vanguard.rebalance.model;

import com.opencsv.bean.CsvBindByName;
import java.util.Objects;

public class Customer {

  @CsvBindByName
  private long customerId;
  @CsvBindByName
  private String email;
  @CsvBindByName
  private String dateOfBirth;
  @CsvBindByName
  private int riskLevel;
  @CsvBindByName
  private int retirementAge;
//  private double stocksPercentage;
//  private double cashPercentage;
//  private double bondsPercentage;

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public int getRiskLevel() {
    return riskLevel;
  }

  public void setRiskLevel(int riskLevel) {
    this.riskLevel = riskLevel;
  }

  public int getRetirementAge() {
    return retirementAge;
  }

  public void setRetirementAge(int retirementAge) {
    this.retirementAge = retirementAge;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "customerId=" + customerId +
        ", email='" + email + '\'' +
        ", dateOfBirth='" + dateOfBirth + '\'' +
        ", riskLevel=" + riskLevel +
        ", retirementAge=" + retirementAge +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return customerId == customer.customerId &&
        riskLevel == customer.riskLevel &&
        retirementAge == customer.retirementAge &&
        Objects.equals(email, customer.email) &&
        Objects.equals(dateOfBirth, customer.dateOfBirth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, email, dateOfBirth, riskLevel, retirementAge);
  }
}
