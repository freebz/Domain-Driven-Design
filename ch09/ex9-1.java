// 명시적인 제약조건

class Bucket {
   private float capacity;
   private float contents;

   public void pourIn(float addedVolume) {
      if (contents + addedVolume > capacity) {
	 contents = capacity;
      } else {
	 contents = contents + addedVolume;
      }
   }
}


class Bucket {
   private float capacity;
   private float contents;

   public void pourIn(float addedVolume) {
      float volumePresent = contents + addedVolume;
      contents = constrainedCapacity(volumePresent);
   }

   private float constrainedCapacity(float volumePlacedIn) {
      if (volumePlacedIn > capacity) return capacity;
      return volumePlacedIn;
   }
}
