package com.vanguard.rebalance.model;

import com.opencsv.bean.CsvBindByName;

public class Strategy {

  @CsvBindByName
  private long strategyId;
  @CsvBindByName
  private int minRiskLevel;
  @CsvBindByName
  private int maxRiskLevel;
  @CsvBindByName
  private int minYearsToRetirement;
  @CsvBindByName
  private int maxYearsToRetirement;
  @CsvBindByName
  private double stocksPercentage;
  @CsvBindByName
  private double cashPercentage;
  @CsvBindByName
  private double bondsPercentage;

  public long getStrategyId() {
    return strategyId;
  }

  public void setStrategyId(long strategyId) {
    this.strategyId = strategyId;
  }

  public int getMinRiskLevel() {
    return minRiskLevel;
  }

  public void setMinRiskLevel(int minRiskLevel) {
    this.minRiskLevel = minRiskLevel;
  }

  public int getMaxRiskLevel() {
    return maxRiskLevel;
  }

  public void setMaxRiskLevel(int maxRiskLevel) {
    this.maxRiskLevel = maxRiskLevel;
  }

  public int getMinYearsToRetirement() {
    return minYearsToRetirement;
  }

  public void setMinYearsToRetirement(int minYearsToRetirement) {
    this.minYearsToRetirement = minYearsToRetirement;
  }

  public int getMaxYearsToRetirement() {
    return maxYearsToRetirement;
  }

  public void setMaxYearsToRetirement(int maxYearsToRetirement) {
    this.maxYearsToRetirement = maxYearsToRetirement;
  }

  public double getStocksPercentage() {
    return stocksPercentage;
  }

  public void setStocksPercentage(double stocksPercentage) {
    this.stocksPercentage = stocksPercentage;
  }

  public double getCashPercentage() {
    return cashPercentage;
  }

  public void setCashPercentage(double cashPercentage) {
    this.cashPercentage = cashPercentage;
  }

  public double getBondsPercentage() {
    return bondsPercentage;
  }

  public void setBondsPercentage(double bondsPercentage) {
    this.bondsPercentage = bondsPercentage;
  }
}
