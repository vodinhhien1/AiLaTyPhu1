package com.example.ductri.ailatyphu.data;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.os.Environment;
import android.util.Log;

import com.example.ductri.ailatyphu.model.Cauhoi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;


public class QuanLiData extends SQLiteOpenHelper{
    private static final String TAG = "QuanLiData";
    private static final String DATA_NAME = "quanli_cauhoi.sqlite";
    public static final String PATH= Environment.getDataDirectory()+"/data/com.example.ductri.ailatyphu/databases/"+DATA_NAME;
    private static final String TABLE_NAME = "question";
    private static final String ID = "id";
    private static final String QUESTION = "clquestion";
    private static final String CAUA = "caua";
    private static final String CAUB = "caub";
    private static final String CAUC = "cauc";
    private static final String CAUD = "caud";
    private static final String DAPAN = "caudung";
    private static final int VERSION = 1;
    private Context context;
    private SQLiteDatabase database;

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
    }
    public QuanLiData(Context context) {
        super(context,DATA_NAME ,null, VERSION);
        this.context = context;
        copyDataBase(context);
    }

    private void copyDataBase(Context context) {
        File file= new File(PATH);
        if (file.exists()){
            return;
        }
        File parent= file.getParentFile();
        parent.mkdirs();
        try {
            FileOutputStream outputStream=new FileOutputStream(file);
            InputStream inputStream= context.getAssets().open(DATA_NAME);
            byte []b= new byte[1024];
            int count= inputStream.read(b);
            while (count!=-1){
                outputStream.write(b,0,count);
                inputStream.read(b);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openDataBase(){
        database=context.openOrCreateDatabase(DATA_NAME,context.MODE_PRIVATE,null);
    }
    public void closeDaTaBase(){
        database.close();
    }
    
    public ArrayList<Cauhoi> getData(){
        openDataBase();
        ArrayList<Cauhoi> arrQuestions=new ArrayList<>();
            String table = TABLE_NAME;
            String sql="SELECT * FROM "+table+" ORDER BY random() limit 1";
            Cursor cursor= database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                Cauhoi cauhoi = new Cauhoi();
                cauhoi.setId(cursor.getInt(0));
                cauhoi.setCauhoi(cursor.getString(1));
                cauhoi.setCauA(cursor.getString(2));
                cauhoi.setCauB(cursor.getString(3));
                cauhoi.setCauC(cursor.getString(4));
                cauhoi.setCauD(cursor.getString(5));
                cauhoi.setDapan(cursor.getInt(6));
                arrQuestions.add(cauhoi);
            }
            while (cursor.moveToNext());
        }
        closeDaTaBase();
        return arrQuestions;
    }
}