// Cargo에 대한 FACTORY와 생성자

public Cargo copyPrototype(String newTrackingID)


public Cargo newCargo(Cargo prototype, String newTrackingID)
    

public Cargo newCargo(Cargo prototype)


public Cargo(String id) {
   trackingID = id;
   deliveryHistory = new DeliveryHistory(this);
   customerRoles = new HashMap();
}


// Handing Event 추가

public HandlingEvent(Cargo c, String eventType, Date timeStamp) {
   handled = c;
   type = eventType;
   completionTime = timeStamp;
}


public static HandlingEvent newLoading(
   Cargo c, CarrierMovement loadedOnto, Date timeStamp) {
   HandlingEvent result = new HandlingEvent(c, LOADING_EVENT, timeStamp);
   result.setCarrierMovement(loadedOnto);
   return result;
}
