package com.jk.showoffmylittle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    String dbtitle, dbfile, dbname, dbmsg;
    int position;

    ImageView detail_img;
    TextView detail_title, detail_name, detail_msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_name= findViewById(R.id.name);
        detail_title= findViewById(R.id.title);
        detail_msg= findViewById(R.id.msg);
        detail_img= findViewById(R.id.detail_img);

        Intent intentget = getIntent();
//        position = intentget.getIntExtra("position", 0);
        dbname = intentget.getStringExtra("name");
        dbtitle = intentget.getStringExtra("title");
        dbmsg = intentget.getStringExtra("msg");
        dbfile = intentget.getStringExtra("file");




        dbfile= dbfile.replace("./", "/");

        detail_name.setText(dbname);
        detail_title.setText(dbtitle);
        detail_msg.setText(dbmsg);


//        Toast.makeText(this, ""+dbtitle+"\n"+dbfile, Toast.LENGTH_SHORT).show();



        Thread t= new Thread(){
            @Override
            public void run() {
                //Network에 있는 이미지를 읽어와서
                //이미지 뷰에 설정!
                String imgUrl="http://jk2101.dothome.co.kr/showoff"+dbfile;

                //서버 주소까지 연결되는 무지개로드(Stream) 열기
                try {
                    // 무지개로드를 열어주는 해임달 객체 생성
                    URL url= new URL(imgUrl);
                    //무지개로드 열기
                    InputStream is= url.openStream();

                    // Stream을 통해 읽어들인 파일 데이터를
                    //이미지를 가지는 객체로 생성
                    final Bitmap bm= BitmapFactory.decodeStream(is);

                    //별도의 Thread는 UI 변경 작업 불가!!
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            detail_img.setImageBitmap(bm);
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();


//        imgUrl= "http://jk2101.dothome.co.kr/showoff/uploads/"+dbfile;
//        imgUrl= "http://jk2101.dothome.co.kr/showoff/uploads/IMG_20210221183109FB_IMG_1613094152642.jpg";




//        detail_img.setImageResource(Integer.parseInt(imgUrl));

        //http://jk2101.dothome.co.kr/showoff/uploads/IMG_20210221183109FB_IMG_1613094152642.jpg

//        Thread t= new Thread(){
//            @Override
//            public void run() {
//                //Network에 있는 이미지를 읽어와서
//                //이미지 뷰에 설정!
//                imgUrl= "http://jk2101.dothome.co.kr/showoff/uploads/IMG_20210221183109FB_IMG_1613094152642.jpg";
//
//                //서버 주소까지 연결되는 무지개로드(Stream) 열기
//                try {
//                    // 무지개로드를 열어주는 해임달 객체 생성
//                    URL url= new URL(imgUrl);
//                    //무지개로드 열기
//                    InputStream is= url.openStream();
//
//                    // Stream을 통해 읽어들인 파일 데이터를
//                    //이미지를 가지는 객체로 생성
//                    final Bitmap bm= BitmapFactory.decodeStream(is);
//
//                    //별도의 Thread는 UI 변경 작업 불가!!
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            detail_img.setImageBitmap(bm);
//                        }
//                    });
//
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        t.start();








    }
}