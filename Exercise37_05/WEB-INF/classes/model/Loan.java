package model;

public class Loan {
  private double annualInterestRate;
  private int    numberOfYears;
  private double loanAmount;

  public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
    this.annualInterestRate = annualInterestRate;
    this.numberOfYears      = numberOfYears;
    this.loanAmount         = loanAmount;
  }

  public double getMonthlyPayment() {
    double r = annualInterestRate / 1200.0;
    return loanAmount * r / (1 - Math.pow(1 + r, -numberOfYears * 12));
  }
  public double getTotalPayment() { return getMonthlyPayment() * numberOfYears * 12; }
}
