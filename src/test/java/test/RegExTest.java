package test;

public class RegExTest {
	public static void main(String[] args) {
		char a = 'a';
		System.out.println((int)(a));
	}
}

class OuterClass {
	private double d1 = 1.0;
	abstract class InnerOne {
		public abstract double methoda();
	}
}

class A {
	static {
		System.out.print("1");
	}

	public A() {
		System.out.print("2");
	}
}

class B extends A {
	static {
		System.out.print("a");
	}

	public B() {
		System.out.print("b");
	}
}