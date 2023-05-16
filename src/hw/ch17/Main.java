package hw.ch17;

public class Main {
    public static void main(String[] args) {
        System.out.println("20200903 김나령");
        NumberGenerator png = new PrimeNumberGeneartor();
        Observer observer1 = new DigitObserver();
        Observer observer2 = new GraphObserver();
        Observer observer3 = new NamePrintObserver();
        png.addObserver(observer1);
        png.addObserver(observer2);
        png.addObserver(observer3);
        png.execute();
    }
}
