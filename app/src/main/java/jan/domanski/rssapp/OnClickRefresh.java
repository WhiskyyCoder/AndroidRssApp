package jan.domanski.rssapp;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Jan on 2018-04-06.
 */

public class OnClickRefresh implements View.OnClickListener {
    ProgressBar progressBar;

    public OnClickRefresh(ProgressBar progressBar){
        this.progressBar=progressBar;

    }

    @Override
    public void onClick(View view) {

    }
}
