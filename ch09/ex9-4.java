// 화학 창고 포장기

public class ContainerSpecification {
   private ContainerFeture requiredFeature;

   public ContainerSpecification(ContainerFeture required) {
      requiredFeature = required;
   }

   boolean isSatisfiedBy(container aContainer){
      return aContainer.getFeatures().contains(requiredFeature);
   }
}


tnt.setContainerSpecification(new ContainerSpecification(ARMORED));


boolean isSafelyPacked(){
   Iterator it = contents.iterator();
   while (it.hasNext()) {
      Drum drum = (Drum) it.next();
      if (!drum.containerSpecification().isSatisfiedBy(this))
	 return false;
   }
   return true;
}


Iterator it = containers.iterator();
while (it.hasNext()) {
   Container container = (Container) it.next();
   if (!container.isSafelyPacked())
      unsafeContainers.add(container);
}


public interface WarehousePacker {
   public void pack(Collection containersToFill,
      Collection drumsToPack) throws NoAnswerFoundException;

      /* ASSERTION: pack()의 끝에서는 각 Drum의
      Containerspecification이 Container에 의해 충족된다.
      완전한 해결책을 찾을 수 없다면 예외를 던진다.
      */
}
