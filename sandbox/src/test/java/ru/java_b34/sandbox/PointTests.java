package ru.java_b34.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testArea() {
    Point p1 = new Point(-1, -3);
    Point p2 = new Point(2, 1);
    Assert.assertEquals(p1.distance(p2), 5.0, "Значения не совпадают");

    Point p3 = new Point(1, 1);
    Point p4 = new Point(1, 1);
    Assert.assertEquals(p3.distance(p4), 0.0, "Значения не совпадают");

    Point p5 = new Point(0, 1);
    Point p6 = new Point(0, 10);
    Assert.assertEquals(p5.distance(p6), 9.0, "Значения не совпадают");
  }
}
