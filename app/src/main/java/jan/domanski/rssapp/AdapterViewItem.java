package jan.domanski.rssapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Jan on 2018-03-12.
 */

public class AdapterViewItem implements AdapterView.OnItemClickListener {
    private List<Item> items;
    private Context context;
    public AdapterViewItem(List<Item> items, Context context){
        this.items=items;
        this.context=context;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item=items.get(position);

            Uri webpage = Uri.parse(item.getLink());
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }


    }
}
