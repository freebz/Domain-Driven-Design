// 절차적인 방식에서 모델 주도적인 방식으로

abstract class AbstractNet {
   private Set rules;

   void assignRule(LayoutRule rule) {
      rules.add(rule);
   }

   Set assignRules() {
      return rules;
   }
}

class Net extends AbstractNet {
   private Bus bus;

   Set assignRules() {
      Set result = new HashSet();
      result.addAll(super.assignRules());
      result.addAll(bus.assignRules());
      return result;
   }
}


public void testBusRuleAssignment() {
   Net a0 = new Net("a0");
   Net a1 = new Net("a1");
   Bus a = new Bus("a");	// 버스가 이름을 토대로 인식하는 것에
   a.addNet(a0);		// 개념적으로 의존하지 않으므로
   a.addNet(a1);		// 버스의 데스트도 그래서는 안 된다.

   NetRule minWidth4 = NetRule.create(MIN_WIDTH, 4);
   a.assignRule(minWidth4);

   assertTrue(a0.assignRules().contains(minWidth4));
   assertEquals(minWidth4, a0.getRule(MIN_WIDTH));
   assertEquals(minWidth4, a1.getRule(MIN_WIDTH));
}


public void assignBusRule(String busName, String ruleType, double parameter) {
   Bus bus = BusRepository.getByName(busName);
   bus.assignRule(NetRule.create(ruleType, parameter));
}


NetRuleExport.write(aFileName, NetRepository.allNets());
