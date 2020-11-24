// 해운 애플리케이션의 두 CONTEXT

/*
departureNode.shippingOperation.vesselVoyageId -> leg.vesselVoyageId
departureNode.shippingOperation.date -> leg.loadDate
departureNode.locationCode -> leg.loadLocationCode
arrivalNode.shippingOperation.date -> leg.unloadDate
arrivalNode.locationCode -> leg.unloadLocationCode
*/


public Itinerary route(RouteSpecification spec) {
   Booking_TransportNetwork_Translator translator =
      new Booking_TransportNetwork_Translator();

   List constraintLocations = translator.convertConstraints(spec);

   // NetworkTraversalService에 접근
   List pathNodes = traversalService.findPath(constraintLocations);

   Itinerary result = translator.convert(pathNodes);

   return result;
}
