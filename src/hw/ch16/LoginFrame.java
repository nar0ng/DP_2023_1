package hw.ch16;

import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginFrame extends Frame implements ActionListener, Mediator {
    // 체크 박스이자 콜리그 역할
    private ColleagueCheckbox checkGuest;
    private ColleagueCheckbox checkLogin;
    private ColleagueCheckbox checkMember;

    private ColleagueTextField textUser;
    private ColleagueTextField textPass;
    private ColleagueTextField textId;

    private ColleagueButton buttonOk;
    private ColleagueButton buttonCancel;
    private Object i;


    // Colleague를 생성하고 배치한 후에 표시한다
    public LoginFrame(String title) {

        super(title); // 없으면 인자 없는 생성자가 만들어짐

        // 배경색을 설정한다
        setBackground(Color.lightGray);

        // 레이아웃 매니저를 사용해 5×3 그리드를 만든다
        // 배치매니저를 gridlayout으로 지정
        setLayout(new GridLayout(5, 3));

        // Colleague를 생성한다
        createColleagues();

        // 배치한다
        add(checkGuest);
        add(checkLogin);
        add(checkMember);
        add(new Label("Username:"));
        add(textUser);
        add(new Label(""));
        add(new Label("Password:"));
        add(textPass);
        add(new Label(""));
        add(new Label("주민등록번호"));
        add(textId);
        add(new Label(""));
        add(buttonOk);
        add(buttonCancel);

        // 활성/비활성 초기 설정을 한다
        colleagueChanged();

        // 표시한다
        pack(); // 컴포넌트를 정리한다
        setVisible(true);
    }

    // Colleague를 생성한다
    @Override
    public void createColleagues() {
        // CheckBox
        // g-> quest와 login은 같은 그룹
        CheckboxGroup g = new CheckboxGroup();
        checkGuest = new ColleagueCheckbox("Guest", g, true);
        checkLogin = new ColleagueCheckbox("Login", g, false);
        checkMember = new ColleagueCheckbox("Member", g, false);

        // TextField
        textUser = new ColleagueTextField("", 10);
        textPass = new ColleagueTextField("", 10);
        textId = new ColleagueTextField("", 10);
        textPass.setEchoChar('*');

        // Button
        buttonOk = new ColleagueButton("OK");
        buttonCancel = new ColleagueButton("Cancel");

        // Mediator를 설정한다
        // 중재자 지정, loginframe이 mediater 역할을 하니까
        checkGuest.setMediator(this);
        checkLogin.setMediator(this);
        checkMember.setMediator(this);
        textUser.setMediator(this);
        textPass.setMediator(this);
        textId.setMediator(this);
        buttonOk.setMediator(this);
        buttonCancel.setMediator(this);

        // Listener 설정
        checkGuest.addItemListener(checkGuest);
        checkLogin.addItemListener(checkLogin);
        checkMember.addItemListener(checkMember);
        textUser.addTextListener(textUser);
        textPass.addTextListener(textPass);
        textId.addTextListener(textId);
        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    // Colleage의 상태가 바뀌면 호출된다
    // 상태가 바뀌면 중재자의 colleageueChange 호출-> 중재의 역할
    @Override
    public void colleagueChanged() {
        if (checkGuest.getState()) { // Guest 체크박스가 눌러졌으면...
            // 게스트 로그인
            textUser.setColleagueEnabled(false); // 비활성화
            textPass.setColleagueEnabled(false); // 비활성화
            textId.setColleagueEnabled(false); // 비활성화
            buttonOk.setColleagueEnabled(false); // 비활성화
        } else if (checkLogin.getState()) { // member이 눌러졌으면
            textUser.setColleagueEnabled(true);
            userpassChanged();
        } else {
            // 사용자 로그인
            textUser.setColleagueEnabled(true);
            userpassChanged();
        }
    }
    private void userpassChanged() {
        if (textUser.getText().length() > 0) { // 유저네임 칸에 문자열이 입력되면
            textPass.setColleagueEnabled(true);
            if (textPass.getText().length() > 0) { // 비밀번호 칸에 문자열이 입력되면
                textId.setColleagueEnabled(true);
                if(textId.getText().length() > 0){ // 주민번호가 입력되면
                    String input = textId.getText();
                    boolean isAllDigits = true;
                    for (int i = 0; i < input.length(); i++) {
                        if (!Character.isDigit(input.charAt(i))) {
                            isAllDigits = false;
                            break;
                        }
                    }
                    if (!isAllDigits) { // 주민번호에 숫자가 아닌 값을 입력한 경우
                        JOptionPane.showMessageDialog(buttonCancel, "주민등록번호는 모두 숫자로 이루어져야 합니다.", "경고창", JOptionPane.WARNING_MESSAGE);
                        int caretPosition = textId.getCaretPosition();
                        if (caretPosition > 0) {
                            String updatedInput = input.substring(0, caretPosition - 1) + input.substring(caretPosition);
                            textId.setText(updatedInput);
                            textId.setCaretPosition(caretPosition - 1);
                        } else if (input.length() > 1) {
                            String updatedInput = input.substring(1);
                            textId.setText(updatedInput);
                        } else {
                            textId.setText("");
                        }
                    }
                    
                    if (input.length() == 13){
                        buttonOk.setColleagueEnabled(true);
                    } else {
                        buttonOk.setColleagueEnabled(false);
                    }
                } else { 
                    buttonOk.setColleagueEnabled(false);
                }
            } else { // 비밀번호가 입력되지 않은 경우
                textId.setColleagueEnabled(false);
                buttonOk.setColleagueEnabled(false);
            }
        } else { // 아이디가 입력되지 않은 경우
            textPass.setColleagueEnabled(false);
            textId.setColleagueEnabled(false);
            buttonOk.setColleagueEnabled(false);
        }
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString()); // 액션 이벤트를 문자열로 찍는다.
        System.exit(0);
    }
}
