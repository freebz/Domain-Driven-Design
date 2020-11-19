// 패턴 통합하기: 지분 계산(Shares Math)

public class Load {
   private Map shares;

   // 접근 메서드, 생성자, 간단한 메서드는 생략

   public Map distributePrincipalPayment(double paymentAmount) {
      Map paymentShares = new HashMap();
      Map loanShares = getShares();
      double total = getAmount();
      Iterator it = loanShares.keySet().iterator();
      while(it.hasNext()) {
	 Object owner = it.next();
	 double initialLoanShareAmount = getShareAmount(owner);
	 Share paymentShare = new Share(owner, paymentShareAmount);
	 paymentShares.put(owner, paymentShare);

	 double newLoanShareAmount = initialLoanShareAmount - paymentShareAmount;
	 Share newLoanShare = new Share(owner, newLoanShareAmount);
	 loanShares.put(owner, newLoanShare);
      }
      return paymentShares;
   }

   public double getAmount() {
      Map loanShares = getShares();
      double total = 0.0;
      Iterator it = loanShares.keySet().iterator();
      while(it.hasNext()) {
	 Share loanShare = (Share) loanShares.get(it.next());
	 total = total + loanShare.getAmount();
      }
      return total;
   }
}
