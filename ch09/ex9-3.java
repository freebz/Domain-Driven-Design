// 선택(또는 질의)

public Set selectSatisfying(InvoiceSpecification spec) {
   Set results = new HashSet();
   Iterator it = invoices.iterator();
   while (it.hasNext()) {
      Invoice candidate = (Invoice) it.next();
      if (spec.isSatisfiedBy(candidate)) results.add(candidate);
   }

   return results;
}


Set delinquentInvoices = invoiceRepository.selectSatisfying(
   new DelinquentInvoiceSpecification(currentDate));


public String asSQL() {
   return
      "SELECT * FROM INVOICE, CUSTOMER" +
      "  WHERE INVOICE.CUST_ID = CUSTOMER.ID"
      "  AND INVOICE.DUE_DATE + CUSTOMER.GRACE_PERIOD" +
      "     < " + SQLUtility.dateAsSQL(currentDate);
}


public class InvoiceRepository {

   public Set selectWhereGracePeriodPast(Date aDate){
      // 이것은 규칙이 아닌 단순히 특화된 질의에 불과하다
      String sql = whereGracePeriodPast_SQL(aDate);
      ResultSet queryResultSet =
	 SQLDatabaseInterface.instance().executeQuery(sql);
      return buildInvoicesFromResultSet(queryResultSet);
   }

   public String whereGracePeriodPast_SQL(Date aDate) {
      return
	 "SELECT * FROM INVOICE, CUSTOMER" +
	 "  WHERE INVOICE.CUST_ID = CUSTOMER.ID" +
	 "  AND INVOICE.DUE_DATE + CUSTOMER.GRACE_PERIOD" +
	 "     < " + SQLUtility.dateAsSQL(aDate);
   }

   public Set selectSatisfying(InvoiceSpecification spec) {
      return spec.satisfyingElementFrom(this);
   }
}


public class DelinquentInvoiceSpecification {
   // 기본적인 DelinquentInvoiceSpecification 코드
   public Set satisfyingElementsFrom(
                     InvoiceRepository repository) {
      // 채널 규칙은 다음과 같이 정의된다:
      //   "현재 날짜를 기준으로 유예기간이 지남"
      return repository.selectWhereGracePeriodPast(currentDate);
   }
}


public class InvoiceRepository {

   public Set selectWhereDueDateIsBefore(Date aDate) {
      String sql = whereDueDateIsBefore_SQL(aDate);
      ResultSet queryResultSet =
	 SQLDatabaseInterface.instance().executeQuery(sql);
      return buildInvoicesFromResultSet(queryResultSet);
   }

   public String whereDueDateIsBefore_SQL(Date aDate) {
      return
	 "SELECT * FROM INVOICE" +
	 "  WHERE INVOICE.DUE_DATE" +
	 "     < " + SQLUtility.dateAsSQL(aDate);
   }

   public Set selectSatisfying(InvoiceSpecification spec) {
      return spec.satisfyingElementsFrom(this);
   }
}

public class DelinquentInvoiceSpecification {
   // 기본적인 DelinquentInvoiceSpecification 코드

   public Set satisfyingElementsFrom(InvoiceRepository repository) {
      Collection pastDueInvoices =
	 repository.selectWhereDueDateIsBefore(currentDate);

      Set delinquentInvoices = new HashSet();
      Iterator it = pastDueInvoices.iterator();
      while (it.hasNext()) {
	 Invoice anInvoice = (Invoice) it.next();
	 if (this.isSatisfiedBy(anInvoice))
	    delinquentInvoices.add(anInvoice);
      }
      return delinquentInvoices;
   }
}
