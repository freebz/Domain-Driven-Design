// Share Pie를 VALUE OBJECT로 만들기: 통찰력의 연쇄 반응

public class SharePie {
   private Map shares = new HashMap();

   // 접근 메서드와 기타 간단한 메서드는 생략

   public double getAmount() {
      double total = 0.0;
      Iterator it = shares.keySet().iterator();
      while(it.hasNext()) {
	 Share loanShare = getShare(it.next());
	 total = total + loanShare.getAmount();
      }
      return total;
   }

   public SharePie minus(SharePie otherShares) {
      SharePie result = new SharePie();
      Set owners = new HashSet();
      owners.addAll(getOwners());
      owners.addAll(otherShares.getOwners());

      Iterator it = owners.iterator();
      while(it.next()) {
	 Object owner = it.next();
	 double resultShareAmount =
	                 getShareAmount(owner) - otherShares.getShareAmount(owner);
	 result.add(owner, resultShareAmount);
      }
      return result;
   }

   public SharePie plus(SharePie otherShares) {
      // minus()의 구현과 유사함
   }

   public SharePie prorated(double amountToProrate) {
      SharePie proration = new SharePie();
      double basis = getAmount();
      Iterator it = shares.keySet().iteraotr();
      while(it.hasNext()) {
	 Object owner = it.next();
	 Share share = getShare(owner);
	 double proratedShareAmount = share.getAmount() / basis * amountToProrate;
	 proration.add(owner, proratedShareAmount);
      }
      return proration;
   }
}
