// 증권계좌의 연관관계

public class BrokerageAccount {
   String accountNumber;
   Customer customer;
   Set investments;

   // 생성자 등은 생략

   public Customer getCustomer() {
      return customer;
   }
   public Set getInvestments() {
      return investments;
   }
}


public class BrokerageAccount {
   String accountNumber;
   String customerSocialSecurityNumber;

   // 생성자 등은 생략

   public Customer getCustomer() {
      String sqlQuery =
	 "SELECT * FROM CUSTOMER WHERE " +
	 "SS_NUMBER='"+customerSocialSecurityNumber+"'";
      return QueryService.findSingleCustomerFor(sqlQuery);
   }

   public Set getInvestments() {
      String sqlQuery =
	 "SELECT * FROM INVESTMENT WHERE " +
	 "BROKERAGE_ACCOUNT='"+accountNumber+"'";
      return QueryService.findInvestmentsFor(sqlQuery);
   }
}


public class BrokerageAccount {
   String accountNumber;
   Customer customer;
   Map investments;

   // 생성자 등은 생략

   public Customer getCustomer() {
      return customer;
   }

   public Investment getInvestment(String stockSymbol) {
      return (Investment)investments.get(stockSymbol);
   }
}


public class BrokerageAccount {
   String accountNumber;
   String customerSocialSecurityNumber;

   // 생성자 등은 생략

   public Customer getCustomer() {
      String sqlQuery = "SELECT * FROM CUSTOMER WHERE SS_NUMBER='"
	 + customerSocialSecurityNumber + "'";
      return QueryService.findSingleCustomerFor(sqlQuery);
   }

   public Investment getInvestment(String stockSymbol) {
      String sqlQuery = "SELECT * FROM INVESTMENT "
	 + "WHERE BROKERAGE_ACCOUNT='" + accountNumber + "' "
	 + "AND STOCK_SYMBOL='" + stockSymbol +"'";
      return QueryService.findInvestmentsFor(sqlQuery);

   }
}
