// 리팩터링: 페인트 혼합 애플리케이션

public void paint(Paint paint) {
   v = v + paint.getV(); // 혼합한 후에는 페인트의 용량을 합산
   // 새로운 r, b, y 값을 할당하는
   // 복잡한 색상 혼합 로직의 코드는 생략
}


public void testPaint() {
   // 용량이 100인 순수한 노란색 페인트를 생성한다.
   Paint yellow = new Paint(100.0, 0, 50, 0);
   // 용량이 100인 순수한 파란색 페인트를 생성한다.
   Paint blue = new Paint(100.0, 0, 0, 50);

   // 노란색 페인트에 파란색 페인트를 혼합한다.
   yellow.paint(blue);

   // 혼합한 결과는 용량이 200.0인 초록색 페인트여야 한다.
   assertEquals(200.0, yellow.getV(), 0.01);
   assertEquals(25, yellow.getB());
   assertEquals(25, yellow.getY());
   assertEquals(0, yellow.getR());
}


public void testPaint() {
   // 용량이 100인 순수한 노람색 페인트로 테스트를 시작한다.
   Paint ourPaint = new Paint(100.0, 0, 50, 0);
   // 용량이 100인 순수한 파란색 페인트를 생성한다.
   Paint blue = new Paint(100.0, 0, 0, 50);

   // 노란색 페인트에 파란색 페인트를 혼합한다.
   ourPaint.mixIn(blue);

   // 혼합한 결과는 용량이 200.0인 초록색 페인트여야 한다.
   assertEquals(200.0, ourPaint.getVolume(), 0.01);
   assertEquals(25, ourPaint.getBlue());
   assertEquals(25, ourPaint.getYellow());
   assertEquals(0, ourPaint.getRed());
}
