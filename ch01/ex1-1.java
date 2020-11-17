// 감춰진 개념 추출하기

public int makeBooking(Cargo cargo, Voyage voyage) {
   int confimation = orderConfimationSequence.next();
   voyage.addCargo(cargo, confimation);
   return confimation;
}


public int makeBooking(Cargo cargo, Voyage voyage) {
   double maxBooking = voyage.capacity() * 1.1;
   if ((voyage.bookedCargoSize() + cargo.size()) > maxBooking)
      return -1;
   int confimation = orderConfimationSequence.next();
   voyage.addCargo(cargo, confimation);
   return confimation;
}


public int makeBooking(Cargo cargo, Voyage voyage) {
   if (!overbookingPolicy.isAllowed(cargo, voyage)) return -1;
   int confimation = orderConfimationSequence.next();
   voyage.addCargo(cargo, confimation);
   return confimation;
}


public boolean isAllow(Cargo cargo, Voyage voyage) {
   return (cargo.size() + voyage.bookedCargoSize()) <= (voyage.capacity() * 1.1);
}
