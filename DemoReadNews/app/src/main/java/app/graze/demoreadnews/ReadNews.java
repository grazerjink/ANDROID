package app.graze.demoreadnews;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ReadNews extends AppCompatActivity {

    String VnExpress = "http://vnexpress.net/rss/tin-moi-nhat.rss";
    SwipeRefreshLayout refreshLayout;
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_news);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                getTask("http://vnexpress.net/rss/tin-moi-nhat.rss");
            }
        });
        getTask("http://vnexpress.net/rss/tin-moi-nhat.rss");
    }

    void getTask(String path) {
        try {
            new MyAsyncTask().execute(new URL(path));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    void initRecyclerView(List<News> newses) {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(this,this, R.layout.item_rss_news, newses);
        } else {
            newsAdapter.clear();
            newsAdapter.addAll(newses);
            newsAdapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        }
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class MyAsyncTask extends AsyncTask<URL, Void, List<News>> {

        @Override
        protected List<News> doInBackground(URL... urls) {
            URLConnection urlConnection = null;
            List<News> newsList = new ArrayList<>();
            try {
                urlConnection = urls[0].openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                int respCode = httpURLConnection.getResponseCode();
                if (respCode == HttpURLConnection.HTTP_OK) {
                    InputStream is = httpURLConnection.getInputStream();
                    newsList = parseRSSVnExpress(is);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsList;
        }

        private List<News> parseRSSVnExpress(InputStream is) {
            List<News> list = new ArrayList<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(is);
                if (document != null) {
                    NodeList itemList = document.getElementsByTagName("item");
                    if (itemList != null) {
                        for (int i = 0; i < itemList.getLength(); i++) {
                            Node item = itemList.item(i);
                            News news = new News();
                            NodeList childrenItems = item.getChildNodes();
                            for (int j = 0; j < childrenItems.getLength(); j++) {
                                Node child = childrenItems.item(j);
                                String contents = child.getTextContent();
                                switch (child.getNodeName()) {
                                    case News.TITLE:
                                        news.setTitle(contents);
                                        break;
                                    case News.DESCRIPTION:
                                        String temp[] = contents.split("</br>");
                                        //temp[0] chua image, temp[1] chua description
                                        news.setDescription(temp[1].split("]]>")[0].trim());
                                        news.setImgLink(temp[0].split("src=\"")[1].split("\"")[0].trim());
                                        break;
                                    case News.PUB_DATE:
                                        news.setPubDate(contents);
                                        break;
                                    case News.LINK:
                                        news.setLinkDetail(contents);
                                        break;
                                }
                            }
                            list.add(news);
                        }
                    }
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<News> newses) {
            super.onPostExecute(newses);
            initRecyclerView(newses);
        }
    }
}