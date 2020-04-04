package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import com.example.miniproject.SortClass;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<InfoResults> list;
    InfoAdapter infoAdapter;
    Handler handler;
    ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        listView = (ListView) findViewById(R.id.listview);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                SortClass sortClass = new SortClass();
                Collections.sort(list, sortClass);
                infoAdapter = new InfoAdapter(MainActivity.this, list);
                listView.setAdapter(infoAdapter);
                super.handleMessage(msg);
            }
        };
        list = new ArrayList<InfoResults>();
        strings = new ArrayList<String>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    strings.add("section.notice.fr");
                    strings.add("section.news.fl");
                    strings.add("div.more-list-item.ztxx");
                    strings.add("div.more-list-item.zsxx");
                    strings.add("div.more-list-item.zxns");
                    strings.add("div.xxgk.more-list-item");
                    strings.add("section.xsjl.fr");
                    Document document = Jsoup.connect("http://www.cse.cqu.edu.cn").get();
                    for (int j = 0; j < strings.size(); j++) {
                        Elements elements = document.select(strings.get(j));
                        Elements lists = elements.select("ul.list");
                        Elements a = lists.select("a");
                        Elements h3 = elements.select("title.clr").select("h3");
                        Elements span = lists.select("span");
                        for (int i = 0; i < span.size(); i++) {//lists.size()
                            InfoResults results = new InfoResults();
                            Element element = a.get(i);
                            Element element1 = span.get(i);
                            results.date = element1.text();
                            results.title = element.text();
                            results.link = element.attr("abs:href");
                            list.add(results);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        });
        thread.start();
        handler.sendEmptyMessage(0);


        //sort(list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", list.get(position).title);
                bundle.putString("link", list.get(position).link);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
