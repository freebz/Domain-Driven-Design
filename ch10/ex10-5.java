// 논리 연산을 이용한 SPECIFICATION 조합

public interface Specification {
   boolean isSatisfiedBy(Object candidate);
}


public class ContainerSpecification implements Specification {
   private ContainerFeature requiredFeature;

   public ContainerSpecification(ContainerFeature required) {
      requiredFeature = required;
   }

   boolean isSatisfiedBy(Object candidate){
      if (!candidate instanceof Container) return false;

      return (Container)candidate.getFeatures().contains(requiredFeature);
   }
}


public interface Specification {
   boolean isSatisfiedBy(Object candidate);

   Specification and(Specification other);
   Specification or(Specification other);
   Specification not();
}


Specification ventilated = new ContainerSpecification(VENTILATED);
Specification armored = new ContainerSpecification(ARMORED);

Specification both = ventilated.and(armored);


Specification ventilatedType1 = new ContainerSpecification(VENTILATED_TYPE_1);
Specification ventilatedType2 = new ContainerSpecification(VENTILATED_TYPE_2);

Specification either = ventilatedType1.or(ventilatedType2);


Specification cheap = (ventilated.not()).and(armored.not());


public abstract class AbstractSpecification implements Specification {
   public Specification and(Specification other) {
      return new AndSpecification(this, other);
   }
   public Specification or(Specification other) {
      return new OrSpecification(this, other);
   }
   public Specification not() {
      return new NotSpecification(this);
   }
}

public class AndSpecification extends AbstractSpecification {
   Specification one;
   Specification other;
   public AndSpecification(Specification x, Specification y) {
      one = x;
      other = y;
   }
   public boolean isSatisfiedBy(Object candidate) {
      return one.isSatisfiedBy(candidate) &&
	 other.isSatisfiedBy(candidate);
   }
}

public class OrSpecification extends AbstractSpecification {
   Specification one;
   Specification other;
   public OrSpecification(Specification x, Specification y) {
      one = x;
      other = y;
   }
   public boolean isSatisfiedBy(Object candidate) {
      return one.isSatisfiedBy(candidate) ||
	 other.isSatisfiedBy(candidate);
   }
}

public class NotSpecification extends AbstractSpecification {
   Specification wrapped;

   public NotSpecification(Specification x) {
      wrapped = x;
   }
   public boolean isSatisfiedBy(Object candidate) {
      return !wrapped.isSatisfiedBy(candidate);
   }
}
