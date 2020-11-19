// 페인트 혼합 애플리케이션을 다시 리팩터링하기

public void mixIn(Paint other) {
   volume = volume.plus(other.getVolume());
   // 새로운 빨강, 파랑, 노랑 값을 할당하는
   // 많은 양의 복잡한 색상 혼합 코드가 이어짐
}


public class PigmentColor {

   public PigmentColor mixedWith(PigmentColor color, double ratio) {
      // 새로운 빨강, 파랑, 노랑 값을 할당하는
      // 많은 양의 복잡한 색상 혼합 코드가 이어짐
   }
}

public class Paint {

   public void mixIn(Paint other) {
      volume = volume + other.getVolume();
      double ratio = other.getVolume() / volume;
      pigmentColor = pigmentColor.mexedWith(other.pigmentColor(), ratio);
   }
}
