package ru.java_b34.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		System.out.println("Hello, World!");

		Point p = new Point(1,2,4,6);
		System.out.println("Расстояние между двумя точками p1(" + p.x1 + "," + p.y1 + ") и p2(" + p.x2 + "," + p.y2 + ") = " + p.distance());
	}
}