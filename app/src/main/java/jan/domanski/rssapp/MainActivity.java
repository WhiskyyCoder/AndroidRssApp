package jan.domanski.rssapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Item> itemsArrayList;
    ItemAdapter itemAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        itemsArrayList = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, itemsArrayList);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        ReadRssFromSource task = new ReadRssFromSource(itemsArrayList,this,itemAdapter,progressBar);
        task.execute();

        ListView itemsListView = (ListView) findViewById(R.id.item_list_view);
        itemsListView.setOnItemClickListener(new AdapterViewItem(itemsArrayList, this));
        itemsListView.setAdapter(itemAdapter);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_refresh:
                itemsArrayList.clear();
                itemAdapter.notifyDataSetChanged();
                ReadRssFromSource task = new ReadRssFromSource(itemsArrayList,this,itemAdapter,progressBar);
                task.execute();
                break;
            default:
                break;

        }
    return true ;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}


