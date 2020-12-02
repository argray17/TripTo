package com.redhawkride.model.moneyhandling;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/*
   https://stackoverflow.com/questions/1359817/using-bigdecimal-to-work-with-currencies
*/
public class Money {
  private static final Currency USD = Currency.getInstance("USD");
  private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
  private final BigDecimal amount;
  private final Currency currency;

  public static Money dollars(BigDecimal amount) {
    return new Money(amount, USD);
  }

  public Money(BigDecimal amount) {
    this(amount, USD, DEFAULT_ROUNDING);
  }

  Money(BigDecimal amount, Currency currency) {
    this(amount, currency, DEFAULT_ROUNDING);
  }

  Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
    this.currency = currency;
    this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return getCurrency().getSymbol() + " " + getAmount();
  }

  public String toString(Locale locale) {
    return getCurrency().getSymbol(locale) + " " + getAmount();
  }
}
