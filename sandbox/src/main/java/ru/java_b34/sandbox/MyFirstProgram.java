package ru.java_b34.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		System.out.println("Hello, World!");

		Point p1 = new Point(1,2);
		Point p2 = new Point(4,6);
		System.out.println("Расстояние между двумя точками p1(" + p1.x + "," + p1.y + ") и p2(" + p2.x + "," + p2.y + ") = " + p1.distance(p2));
	}
}