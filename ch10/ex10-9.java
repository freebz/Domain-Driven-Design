// 암시적인 개념을 명확하게 만들기

public class Loan {
   private SharePie shares;

   // 접근 메서드, 생성자, 간단한 메서드는 생략

   public Map calculatePrincipalPaymentDistribution(double paymentAmount) {
      return getShares().prorated(paymentAmount);
   }
   public void applyPrincipalPayment(Map paymentShares) {
      shares.decrease(paymentShares);
   }
}
