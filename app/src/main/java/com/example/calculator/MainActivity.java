package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private TextView Screen;
private Button AC, power, back, div,seven, eight,
        nine,mul, four, five, six,minus,one, two,
        three, add,zero,point,Ans,equal;
private String input,Answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Screen=findViewById(R.id.screen);
        AC=findViewById(R.id.ac);
        power=findViewById(R.id.power);
        back=findViewById(R.id.back);
        div=findViewById(R.id.div);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        mul=findViewById(R.id.mul);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        minus=findViewById(R.id.minus);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        add=findViewById(R.id.add);
        zero=findViewById(R.id.zero);
        point=findViewById(R.id.point);
        Ans=findViewById(R.id.Ans);
        equal=findViewById(R.id.equal);

        input ="";
        Answer = "";

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //add onclick listener for each button
    public void ButtonClick(View view){
        Button button=(Button) view;
        String data= button.getText().toString();
        switch (data){
            case"AC":
                input="";
                break;
            case "Ans":
                if (Answer != null && !Answer.isEmpty()) {
                    input += Answer;
                }
                break;
            case"x":
                Solve();
                input+="*";
                break;
            case "^":
                Solve();
                 input+="^";
                 break;
            case "=":
                Solve();
                if (!input.equals("Error")) {
                    Answer = input;
                }
            break;
            case "⌫":
                String newText=input.substring(0,input.length()-1);
                input=newText;
                break;
            default:
                if (input == null){
                    input= "";
                }
                if (data.equals("+")||data.equals("-")||data.equals("/")){
                    Solve();
                }
                input += data;

        }
        Screen.setText(input);
    }
    private void Solve(){
        if (input.split("\\*").length==2){
            String number[]=input.split("\\*");
            try {
                double mul=Double.parseDouble(number[0])*Double.parseDouble(number[1]);
                input=mul + "";
            }
            catch (Exception e){
                //toggle error
            }
        } else if (input.split("/").length==2){
            String number[]=input.split("/");
            try {
                double div = Double.parseDouble(number[0])/Double.parseDouble(number[1]);
                input=div + "";
            }
            catch (Exception e){
                //toggle error
            }
        }
        else if (input.split("\\^").length==2) {
            String number[] = input.split("\\^");
            try {
                double pow =Math.pow(Double.parseDouble(number[0]),Double.parseDouble(number[1])) ;
                input = pow+"";
            } catch (Exception e) {
                //togle error
            }
        }
        else if (input.split("\\+").length==2) {
            String number[] = input.split("\\+");
            try {
                double add =Double.parseDouble(number[0]) + Double.parseDouble(number[1]) ;
                input = add+ "";
            } catch (Exception e) {
                //togle error
            }
        }
        else if (input.split("-").length>1) {
            String number[] = input.split("-");
            //to subtract from negative number like 5-8
            if (number[0].equals("") && number.length==2){
                number[0]="0";
            }
            try {
                double minus=0;
                if (number.length==2){
                    minus = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                }
                else if (number.length==3){
                    minus = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                }
                input = minus + "";
            } catch (Exception e) {
                //togle error
            }
        }
        //to remove the last digit .0 from integer result like 9.0 instead of 9
        String n[]=input.split("\\.");
        if (n.length>1){
            if(n[1].equals("0")){
                input=n[0];
            }
        }
        Screen.setText(input);
    }
}