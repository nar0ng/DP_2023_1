package hw.ch19;

public class Main {
    public static void main(String[] args) {
        System.out.println("20200903 김나령");
        SafeFrame frame = new SafeFrame("State Sample");
        while (true) { 
            for (int hour = 15; hour < 24; hour++) {
                frame.setClock(hour);   // 시간 설정
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
    