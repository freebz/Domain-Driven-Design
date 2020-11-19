// 새로운 설계의 유연함

public class Loan {
   private SharePie shares;

   // 접근 메서드, 생성자, 간단한 메서드는 생략

   public SharePie calculatePrincipalPaymentDistribution(double paymentAmount) {
      return shares.prorated(paymentAmount);
   }

   public void applyPrincipalPayment(SharePie paymentShares) {
      setShares(shares.minus(paymentShares));
   }
}


public class Facility {
   private SharePie shares;
   ...
   public SharePie calculateDrawdownDefaultDistribution(double drawdownAmount) {
      return shares.prorated(drawdownAmount);
   }
}

public class Loan {
   ...
   public void applyDrawdown(SharePie drawdownShares) {
      setShares(shares.plus(drawdownShares));
   }
}


SharePie originalAgreement = aFacility.getShares().prorated(aLoan.getAmount());
SharePie actual = aLoan.getShares();
SharePie deviation = actual.minus(originalAgreement);
