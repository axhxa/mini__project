package com.example.miniproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class JsoupUtils {
    static JsoupUtils instance;

    public static JsoupUtils getInstance() {
        if(instance==null){
            instance=new JsoupUtils();
        }
        return instance;
    }

    /*public void getInfo(String url, String title, String date, String sort, final ArrayList<InfoResults> list, final CallBack mCallBack) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://www.cse.cqu.edu.cn").get();
                    System.out.println(document);
                    //Elements elements=document.select("class.notice").select("class.list").select("a[href]");
                    Elements elements = document.getElementsByClass(".notice.fr");
                    Elements lists = elements.select(".ul.list");
                    for (int i = 0; i < lists.size(); i++) {
                        InfoResults results = new InfoResults();
                        Element element = elements.get(i);
                        results.date = "0";
                        results.title = "i" + i;
                        results.sort = "0";
                        String url = element.attr("href");
                        System.out.println(url);
                        list.add(results);
                    }
                    if (list != null) {
                        mCallBack.Success(list);
                    } else mCallBack.Error(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface CallBack{
         void Success(ArrayList<InfoResults> list);
         void Error(ArrayList<InfoResults> list);
    }*/
}

