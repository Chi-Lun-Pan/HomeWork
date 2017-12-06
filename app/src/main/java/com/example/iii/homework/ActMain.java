package com.example.iii.homework;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ActMain extends AppCompatActivity {

    String[] drinks ={"紅茶 20元","綠茶 25元","奶茶 30元"};
    String[] Count = {"1","2","3","4","5","6","7","8","9","10"};
    ArrayList<String> save = new ArrayList<String>();
    int Total=0;
    String[] List;
    int pay = 0;

    private DialogInterface.OnClickListener btnReceiver_click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            etDrink.setText((drinks[which]));
        }
    };

    private View.OnClickListener btnDrink_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ActMain.this);
            builder.setTitle("選擇飲料");
            builder.setItems(drinks,btnReceiver_click);
            Dialog message = builder.create();
            message.show();
        }
    };
    private DialogInterface.OnClickListener btnReceive_click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            etCount.setText((Count[which]));
        }
    };

    private View.OnClickListener btnCount_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ActMain.this);
            builder.setTitle("選擇數量");
            builder.setItems(Count,btnReceive_click);
            Dialog message = builder.create();
            message.show();
        }
    };
    private View.OnClickListener btnPay_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(etPay.getText().length()==0)
            {
                pay+=1000;
                etPay.setText(String.valueOf(pay));
            }
            else {
                pay = Integer.parseInt(etPay.getText().toString());
                pay += 1000;
                etPay.setText(String.valueOf(pay));
            }
        }
    };
    private View.OnClickListener btnSave_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(etDrink.getText().length()>0 && etCount.getText().length()>0)
            {
                save.add("餐點 : "+(etDrink.getText().toString())+"   數量 : "+(etCount.getText().toString())+" 杯");
                switch (etDrink.getText().toString()){
                    case "紅茶 20元" :
                        Total+=(20*Integer.parseInt(etCount.getText().toString()));
                        break;
                    case "綠茶 25元" :
                        Total+=(25*Integer.parseInt(etCount.getText().toString()));
                        break;
                    case "奶茶 30元" :
                        Total+=(30*Integer.parseInt(etCount.getText().toString()));
                        break;
                    default:
                        break;
                }
                etTotal.setText(String.valueOf(Total));
                Toast message = Toast.makeText(ActMain.this,"新增資料成功",Toast.LENGTH_LONG);
                message.show();
            }
        }
    };
    private DialogInterface.OnClickListener btnReceived_click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            closeContextMenu();
        }
    };

    private View.OnClickListener btnList_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            List = new String[save.size()];
            for(int i=0;i<save.size();i++)
            {
                List[i]=save.get(i);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(ActMain.this);
            builder.setTitle("選擇想要的號碼");
            builder.setItems(List,btnReceived_click);
            Dialog message = builder.create();
            message.show();
        }
    };
    private View.OnClickListener btngo_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pay = Integer.parseInt(etPay.getText().toString());
            int finish = pay-Total;
            etBack.setText(String.valueOf(finish));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actmain);
        InitialComponent();
    }

    public void InitialComponent(){
        btnDrink = (Button)findViewById(R.id.btndrink);
        btnDrink.setOnClickListener(btnDrink_click);
        btnCount = (Button)findViewById(R.id.btnCount);
        btnCount.setOnClickListener(btnCount_click);
        btnPay = (Button)findViewById(R.id.btnpay);
        btnPay.setOnClickListener(btnPay_click);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(btnSave_click);
        btnList = (Button)findViewById(R.id.btnList);
        btnList.setOnClickListener(btnList_click);
        btngo = (Button)findViewById(R.id.btngo);
        btngo.setOnClickListener(btngo_click);
        etDrink = (EditText)findViewById(R.id.etDrink);
        etCount = (EditText)findViewById(R.id.etCount);
        etTotal = (EditText)findViewById(R.id.etTotal);
        etPay = (EditText)findViewById(R.id.etPay);
        etBack = (EditText)findViewById(R.id.etBack);
    }

    Button btnDrink;
    Button btnCount;
    Button btnPay;
    Button btnList;
    Button btnSave;
    Button btngo;
    EditText etDrink;
    EditText etCount;
    EditText etTotal;
    EditText etPay;
    EditText etBack;
}
