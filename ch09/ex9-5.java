// 동작하는 창고 포장기 프로토타입

public class Container {
   private double capacity;
   private Set contents; // 드럼통

   public boolean hasSpaceFor(Drum aDrum) {
      return remainingSpace() >= aDrum.getSize();
   }

   public double remainingSpace() {
      double totalContentSize = 0.0;
      Iterator it = contents.iterator();
      while (it.hasNext()) {
	 Drum aDrum = (Drum) it.next();
	 totalContentSize = totalContentSize + aDrum.getSize();
      }
      return capacity - totalContentSize;
   }

   public boolean canAccommodate(Drum drum) {
      return hasSpaceFor(aDrum) &&
	 aDrum.getContainerSpecification().isSatisfiedBy(this);
   }

}


public class PrototypePacker implements WarehousePacker {

   public void pack(Collection containers, Collection drums)
         throws NoAnswerFoundException {

      /* 이 메서드는 작성된 바대로 ASSERTION을 이행한다.
      하지만 예외가 던져지면 Container의 내용물은 바뀔 수도 있다.
      롤백은 반드시 상위 수준에서 처리해야 한다. */

      Iterator it = drums.iterator();
      while (it.hasNext()) {
	 Drum drum = (Drum) it.next();
	 Container container = findContainerFor(containers, drum);
	 containers.add(drum);
      }
   }

   public Container findContainerFor(Collection containers, Drum drum)
         throws NoAnswerFoundException {
      Iterator it = containers.iterator();
      while (it.hasNext()) {
	 Container container = (Container) it.next();
	 if (container.canAccommodate(date))
	    return container;
      }
      throw new NoAnswerFoundException();
   }

}
