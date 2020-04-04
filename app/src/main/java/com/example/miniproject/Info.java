package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Info extends AppCompatActivity {

    Bundle bundle;
    String link;
    Handler handler;
    LinearLayout linearLayout;
    TextView content;
    String str;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        bundle=this.getIntent().getExtras();
        link=bundle.getString("link");
        init();
        title=findViewById(R.id.title);
        content=findViewById(R.id.content);
    }

    private void init(){
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    content.setText(msg.getData().getString("content"));
                    title.setText(msg.getData().getString("title"));
                }
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(link).get();
                    System.out.println(document);
                    Elements titles = document.select("div.artical-title");
                    Elements contents = document.select("div.artical-content.clr");
                    Elements parameters=contents.select("div.v_news_content");
                    Elements h3 = titles.select("h3");
                    Elements p = parameters.select("p");
                    str="    ";
                    for (int i = 0; i < p.size(); i++) {//lists.size()
                        Element element = p.get(i);
                        str = str +"    "+ element.text() + "\n";
                    }
                    Bundle bundle1=new Bundle();
                    Message message=new Message();
                    bundle1.putString("title",h3.text());
                    bundle1.putString("content",str);
                    message.setData(bundle1);
                    message.what=1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
