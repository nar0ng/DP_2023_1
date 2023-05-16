package hw.ch17;

public class NamePrintObserver implements Observer {
    @Override
    public void update(NumberGenerator generator) {
        int count = generator.getNumber();
        System.out.print("NamePrintObserver:");
        for (int i = 0; i< count; i++){
            System.out.print("김나령 ");
        }
        System.out.println(" ");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
}
