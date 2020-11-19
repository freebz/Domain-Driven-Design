// 포섭 관계

public class MinimumAgeSpecification {
   int threshold;

   public boolean isSatisfiedBy(Person candidate) {
      return candidate.getAge() >= threshold;
   }

   public boolean subsumes(MinimumAgeSpecification other) {
      return threshold >= other.getThreshold();
   }
}


drivingAge = new MinimumAgeSpecification(16);
votingAge = new MinimumAgeSpecification(18);
assertTrue(votingAge.subsumes(drivingAge));


public interface Specification {
   boolean isSatisfiedBy(Object candidate);
   Specification and(Specification other);
   boolean subsumes(Specification other);
}


public boolean subsumes(Specification other) {
   if (other instanceof CompositeSpecification) {
      Collection otherLeaves = (CompositeSpecification) other.leafSpecifications();
      Iterator it = otherLeaves.iterator();
      while (it.hasNext()) {
	 if (!leafSpecifications().contains(it.next()))
	    return false;
      }
   } else {
      if (!leafSpecifications().contains(other))
	 return false;
   }
   return true;
}
