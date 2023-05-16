package hw.ch17;

import java.util.Random;

public class PrimeNumberGeneartor extends NumberGenerator { 
    private Random random = new Random(); 	// 난수 생성기 
    private int number;                   		// 현재 수 

    // 수를 취득한다 
    @Override
    public int getNumber() {
        return number;
    }

    // 소수판별 메소드
    public boolean isPrimeNumber(int num){
        if(number==1) {
			return false;
		}
		for(int i=2 ; i<number ; i++ ) {
			if(number%i==0) {
				return false;
			}
		}
		return true;
    }

    // 수를 생성한다 
    @Override
    public void execute() {
        for (int i = 0; i < 100; i++) {
            number = random.nextInt(100)+1;
            if(isPrimeNumber(number)){
            notifyObservers();
            }
        }
    }

}
