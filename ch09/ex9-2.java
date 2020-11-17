// 검증

class DelinquentInvoiceSpecification extends InvoiceSpecification {
   private Date currentDate;
   // 인스턴스는 한 날짜를 대상으로 사용된 후 폐기된다.

   public DelinquentInvoiceSpecification(Date currentDate) {
      this.currentDate = currentDate;
   }

   public boolean isSatisfiedBy(Invoice candidate) {
      int gracePeriod = candidate.customer().getPaymentGracePeriod();
      Date firmDeadline = DateUtility.addDaysToDate(candidate.dueDate(), gracePeriod);
      return currentDate.after(firmDeadline);
   }

}


public boolean accountIsDelinquent(Customer customer) {
   Date today = new Date();
   Specification delinquentSpec = new DelinquentInvoiceSpecification(today);
   Iterator it = customer.getInvoices().iterator();
   while (it.hasNext()) {
      Invoice candidate = (Invoice) it.next();
      if (delinquentSpec.isSatisfiedBy(candidate)) return true;
   }
   return false;
}
