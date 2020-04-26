package inheritance;

public class Child extends Parent {
    public void method1()
    {
        System.out.println("Method 1 of Child");
    }

    public static void main(String []s)
    {
        Child ch = new Child();
        ch.method1();
    }
}
