package com.example.ductri.ailatyphu;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ductri.ailatyphu.data.QuanLiData;
import com.example.ductri.ailatyphu.model.Cauhoi;

import java.util.ArrayList;

public class MainStart extends AppCompatActivity {
    private TextView CauHoi;
    private Button btnCauA;
    private Button btnCauB;
    private Button btnCauC;
    private Button btnCauD;
    private int caudung;
    private TextView SoCau;
    private int i;
    int coin;
    private QuanLiData dbQuanli;
    private ArrayList<Cauhoi> questions=new ArrayList<>();
    int soMiliGiay = 31000;
    CountDownTimer demThoiGian;
    TextView tvThoiGian;
    TextView tvMoney;
    public void AnhXa(){
        tvThoiGian = (TextView)findViewById(R.id.tv_timeDown);
        tvMoney = (TextView)findViewById(R.id.tvCoin);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_start);
        dbQuanli = new QuanLiData(this);
        questions = dbQuanli.getData();
        CauHoi = (TextView)findViewById(R.id.txt_questions);
        btnCauA = (Button)findViewById(R.id.answer_A);
        btnCauB = (Button)findViewById(R.id.answer_B);
        btnCauC = (Button)findViewById(R.id.answer_C);
        btnCauD = (Button)findViewById(R.id.answer_D);
        SoCau = (TextView)findViewById(R.id.tvSoCau);
        i = 0;
    }
    public void GanDuLieu(int i){
        int cauhoiso = i+1;
        SoCau.setText("Câu hỏi số"+cauhoiso+":");
        CauHoi.setText(questions.get(i).getCauhoi());
        btnCauA.setText("A. "+questions.get(i).getCauA());
        btnCauB.setText("B. "+questions.get(i).getCauB());
        btnCauC.setText("C. "+questions.get(i).getCauC());
        btnCauD.setText("D. "+questions.get(i).getCauD());
        switch (questions.get(i).getDapan()){
            case 1:
                caudung=R.id.answer_A;
                break;
            case 2:
                caudung=R.id.answer_B;
                break;
            case 3:
                caudung=R.id.answer_C;
                break;
            case 4:
                caudung=R.id.answer_D;
                break;
        }
    }

    public void thoigian() {
        demThoiGian = new CountDownTimer(soMiliGiay, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvThoiGian.setText("imCoin:" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                tvThoiGian.setText("done");
            }
        }.start();
    }
}
