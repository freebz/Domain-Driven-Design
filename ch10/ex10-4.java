// 컬렉션에 포함된 요소의 선택

// Java

Set employees = (Employee 객체로 구성된 Set);
Set lowPaidEmployees = new HashSet();
Iterator it = employees.iterator();
while (it.hasNext()) {
    Employee anEmployee = it.next();
    if (anEmployee.salary() < 40000)
	lowPaidEmployees.add(anEmployee);
}


// Smalltalk

employees := (Employee 객체로 구성된 Set).
lowPaidEmployees := employees select:
         [:anEmployee | anEmployee salary < 40000].
