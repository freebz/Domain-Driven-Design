// COMMAND와 SIDE-EFFECT-FREE FUNCTION의 분리

public void applyPrincipalPaymentShares(Map paymentShares) {
   Map loanShares = getShares();
   Iterator it = paymentShares.keySet().iterator();
   while(it.hasNext()) {
      Object lender = it.next();
      Share paymentShare = (Share) paymentShares.get(lender);
      Share loanShare = (Share) loanShares.get(lender);
      double newLoanShareAmount = loanShare.getAmount() - paymentShare.getAmount();
      Share newLoanShare = new Share(lender, newLoanShareAmount);
      loanShares.put(lender, newLoanShare);
   }
}

public Map calculatePrincipalPaymentShares(double paymentAmount) {
   Map paymentShares = new HashMap();
   Map loanShares = getShares();
   double total = getAmount();
   Iterator it = loanShares.keySet().iterator();
   while(it.hasNext()) {
      Object lender = it.next();
      Share loanShare = (Share) loanShare.get(lender);
      double paymentShareAmount = loanShare.getAmount() / total * paymentAmount;
      Share paymentShare = new Share(lender, paymentShareAmount);
      paymentShares.put(lender, paymentShare);
   }
   return paymentShares;
}


Map distribution = aLoan.calculatePrincipalPaymentShares(paymentAmount);
aLoan.applyPrincipalPaymentShares(distribution);
