package com.example.ductri.ailatyphu;

import android.os.AsyncTask;
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.view.animation.Animation;
import com.example.ductri.ailatyphu.data.QuanLiData;
import com.example.ductri.ailatyphu.model.Cauhoi;
import com.example.ductri.ailatyphu.R;
import java.util.ArrayList;


public class MainStart extends AppCompatActivity implements View.OnClickListener {
    private TextView CauHoi;
    private Button btnCauA;
    private Button btnCauB;
    private int cho;
    private Button btnCauC;
    private Button btnCauD;
    private int dapan;
    private boolean chay;
    private TextView SoCau;
    private int i;
    private int dem;
    private int coin;
    private Animation animBtn;
    private Dialog dialog;
    private boolean kiemtra;
    private boolean kiemtracautraloi;
    private QuanLiData dbQuanli;
    private ArrayList<Cauhoi> questions = new ArrayList<>();
    private TextView tvThoiGian;
    private TextView tvMoney;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_start);
        dbQuanli = new QuanLiData(this);
        questions = dbQuanli.getData();
        AnhXa();
        asyncTask.execute();
    }
    public void AnhXa() {
        i = 0;
        coin = 0;
        dem = 30;
        cho = 30;
        tvThoiGian = (TextView) findViewById(R.id.tv_timeDown);
        tvMoney = (TextView) findViewById(R.id.tvCoin);
        CauHoi = (TextView) findViewById(R.id.txt_questions);
        btnCauA = (Button) findViewById(R.id.answer_A);
        btnCauB = (Button) findViewById(R.id.answer_B);
        btnCauC = (Button) findViewById(R.id.answer_C);
        btnCauD = (Button) findViewById(R.id.answer_D);
        SoCau = (TextView) findViewById(R.id.tvSoCau);
        GanDuLieu(i);
        btnCauA.setOnClickListener(this);
        btnCauB.setOnClickListener(this);
        btnCauC.setOnClickListener(this);
        btnCauD.setOnClickListener(this);
    }

    public void GanDuLieu(int i) {
        int cauhoiso = i + 1;
        tvMoney.setText(coin+"");
        SoCau.setText("Câu hỏi số" + cauhoiso + ":");
        kiemtracautraloi = true;
        CauHoi.setText(questions.get(i).getCauhoi());
        btnCauA.setText("A. " + questions.get(i).getCauA());
        btnCauB.setText("B. " + questions.get(i).getCauB());
        btnCauC.setText("C. " + questions.get(i).getCauC());
        btnCauD.setText("D. " + questions.get(i).getCauD());
        btnCauA.setEnabled(true);
        btnCauB.setEnabled(true);
        btnCauC.setEnabled(true);
        btnCauD.setEnabled(true);
        dem = 30;
        switch (questions.get(i).getDapan()) {
            case 1:
                dapan = R.id.answer_A;
                break;
            case 2:
                dapan = R.id.answer_B;
                break;
            case 3:
                dapan = R.id.answer_C;
                break;
            case 4:
                dapan = R.id.answer_D;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        chay = false;
        if (!kiemtracautraloi) {
            return;
        }
        if (v.getId() == R.id.answer_A ||
                v.getId() == R.id.answer_B ||
                v.getId() == R.id.answer_C ||
                v.getId() == R.id.answer_D) {
            v.setBackgroundResource(R.drawable.answer_choose);

            if (v.getId() == dapan) {
                kiemtra = true;
                cho = 0;
                coin = coin + 200 * (i + 1);
            } else {
                kiemtra = false;
                cho = 0;
            }
            kiemtracautraloi = false;
        } else if (v.getId() == R.id.help_stop) {
            kiemtracautraloi = false;
            Finish(coin);
        }
    }
    private AsyncTask<Void,Integer,Void> asyncTask=new AsyncTask<Void, Integer, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            for (dem=30;dem>=0;dem--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(dem);
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (chay) {
                tvThoiGian.setText(values[0] + "");
            }
            else{
                dem++;
            }
            cho++;
            if (cho==4 && kiemtra){
                switch (dapan){
                    case R.id.answer_A:
                        btnCauA.setBackgroundResource(R.drawable.answer_true);
                        animButton(btnCauA);
                        break;
                    case R.id.answer_B:
                        btnCauB.setBackgroundResource(R.drawable.answer_true);
                        animButton(btnCauB);
                        break;
                    case R.id.answer_C:
                        btnCauC.setBackgroundResource(R.drawable.answer_true);
                        animButton(btnCauC);
                        break;
                    case R.id.answer_D:
                        btnCauD.setBackgroundResource(R.drawable.answer_true);
                        animButton(btnCauD);
                        break;
                }
            }
            if (cho==4 && !kiemtra){
                switch (dapan){
                    case R.id.answer_A:
                        btnCauA.setBackgroundResource(R.drawable.answer_false);
                        break;
                    case R.id.answer_B:
                        btnCauB.setBackgroundResource(R.drawable.answer_false);
                        break;
                    case R.id.answer_C:
                        btnCauC.setBackgroundResource(R.drawable.answer_false);
                        break;
                    case R.id.answer_D:
                        btnCauD.setBackgroundResource(R.drawable.answer_false);
                        break;
                }
            }
            if (cho==6 && kiemtra) {
                GanDuLieu(++i);
            }
            if ((cho==6 && !kiemtra) || dem==0 || i==16){
                Finish(coin);
            }
        }
    };
    public void animButton(Button btn){
        animBtn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_check);
        btn.startAnimation(animBtn);
    }
    public void Finish(int coin) {
        dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_finish, null);
        TextView tvFinish = (TextView) view.findViewById(R.id.tvFinish);
        tvFinish.setText("Bạn đã dành được " + coin + " điểm. Cảm ơn bạn đã tham gia " +
                "chương trình. Chúc bạn thành công trong cuộc sống !!!");
        Button btnFinish = (Button) view.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setContentView(view);
        dialog.show();

    }

}




