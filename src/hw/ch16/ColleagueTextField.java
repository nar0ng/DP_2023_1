package hw.ch16;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class ColleagueTextField extends TextField implements TextListener, Colleague {
    private Mediator mediator;

    public ColleagueTextField(String text, int columns) {
        super(text, columns);
    }

    // Mediator를 설정한다 
    @Override
    public void setMediator(Mediator mediator) {
        // Mediator을 받아서 담아두는 역할을 한다. 
        this.mediator = mediator;
    }

    // Mediator에서 활성/비활성을 지시한다
    @Override
    public void setColleagueEnabled(boolean enabled) {
          // 중재자가 지시하는 메소드
        // setEnabled는 상속받은 메소드이다. 
        setEnabled(enabled);
      
        // 활성/비활성에 맞게 배경색을 변경한다
        // true: 흰색, false: 회색
        // 부모로부터 물려받은 메소드
        setBackground(enabled ? Color.white : Color.lightGray);
    }

    @Override
    public void textValueChanged(TextEvent e) {
        // 텍스트 이벤트가 발생했을 때 동작한다.
        // 문자열이 변화했으면 Mediator에 알린다
        mediator.colleagueChanged();
    }

    public boolean isDigit(Object charAt) {
        return false;
    }
}
