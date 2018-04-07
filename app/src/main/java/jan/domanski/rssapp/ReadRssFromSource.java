package jan.domanski.rssapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Jan on 2018-03-12.
 */

public class ReadRssFromSource extends AsyncTask<Void, Void, Void> {
    private Context context;
    private Activity activity;
    private final static String urlAddress = "http://www.polsatnews.pl/rss/wszystkie.xml";
    private URL url;
    private List<Item> items;
    private ItemAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    public ReadRssFromSource(List<Item> arrIte, Activity context, ItemAdapter adapter,ProgressBar progressBar) {
        this.context = context.getBaseContext();
        this.activity = context;
        this.progressBar=progressBar;
        arrIte.clear();
        this.items = arrIte;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        progressBar.setVisibility(View.VISIBLE);
        ProcessXML(getData());


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();

            }
        });
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);

    }

    public Document getData() {
        try {
            url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            inputStream.close();
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public void ProcessXML(Document doc) {
        if (doc != null) {
            Element root = doc.getDocumentElement();
            Node channnel = root.getChildNodes().item(1);
            NodeList items = channnel.getChildNodes();

            for (int i = 0; i < items.getLength(); i++) {
                Node item = items.item(i);


                if (item.getNodeName().equalsIgnoreCase("item")) {
                    Item new_item = new Item();
                    NodeList item_nodes = item.getChildNodes();
                    new_item.setTitle(item_nodes.item(1).getTextContent());
                    new_item.setDescription(item_nodes.item(3).getTextContent());
                    new_item.setLink(item_nodes.item(5).getTextContent());
                    new_item.setPubdate(item_nodes.item(7).getTextContent());

                    if (item_nodes.item(9).getNodeName().equalsIgnoreCase("enclosure")) {

                        if (new_item.getImg() == null) {
                            try {
                                InputStream in = (InputStream) new URL(item_nodes.item(9).getAttributes().getNamedItem("url").getTextContent()).getContent();
                                Bitmap bitmap = BitmapFactory.decodeStream(in);
                                new_item.setImg(bitmap);
                                in.close();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                    this.items.add(new_item);

                }


            }


        }


    }


}
