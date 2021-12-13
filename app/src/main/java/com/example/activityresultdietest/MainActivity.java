package com.example.activityresultdietest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mainButton;
    Button mainBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainButton = findViewById(R.id.mainbutton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SubActivity.class);
                //startActivityForResult(i,100);//이걸 바꾸고 싶은데.....

                i.putExtra("resultTag",100);
                ResultTest(i);
              //  mLauncher.launch(i);//option 이라는 건 또 뭐야 ActivityOptionsCompat.makeBasic()
                //저 태그를 가져와야하는데.....


            }
        });
        mainBtn2 = findViewById(R.id.mainButton2);
        mainBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityResultLauncher<Intent> mLauncher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== RESULT_OK){
                            Log.d("s0s",result.getData().toString());
                            Intent k =  result.getData();
                            Uri l =  k.getData();

                           int j = result.getData().getIntExtra("OK",0);//이제 무조건 형식이 무엇인지 알아야한다.
                            //이거 만약 받은 타입이랑 달라지면 아예null값이 뜬다
                            Log.d("ss","s0"+String.valueOf(j));

                        }else {
                            Log.d("ss","s"+String.valueOf(result.getResultCode()));
                           // Log.d("ss","k"+String.valueOf(mLauncher.getClass()));
                        }
                    }
                });
                Intent i = new Intent(getApplicationContext(),SubActivity.class);

                mLauncher2.launch(i);
            }
        });
    }

    void ResultTest(Intent yo){
        ActivityResultLauncher<Intent> mkoko = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Log.d("ko","ko");
                return;
            }
        });
        mkoko.launch(yo);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("d","100");//이걸 뭐로?

    }
    ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()== RESULT_OK){
                Log.d("ss",result.getData().toString());

            }else {
                Log.d("ss","s"+String.valueOf(result.getResultCode()));
                Log.d("ss","k"+String.valueOf(mLauncher.getClass()));
            }

        }
    });

}