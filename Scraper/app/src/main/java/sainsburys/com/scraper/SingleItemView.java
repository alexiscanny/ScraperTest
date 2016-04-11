package sainsburys.com.scraper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Alessio on 09/04/2016.
 */
public class SingleItemView extends ActionBarActivity {
    private ProgressDialog mProgressDialog;
    private String name;
    private String price;
    private String price_unit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.single_item_view);

        // Execute loadSingleView AsyncTask
        new loadSingleView().execute();
    }


    private class loadSingleView extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Remove all views before
            // adding the new ones.
//			mSelectedImagesContainer.removeAllViews();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(SingleItemView.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Spot Opening...");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(String... params) {

            // Retrieve data from ListViewAdapter on click event
            Intent i = getIntent();
            // Get the result of title
            name = i.getStringExtra("name");
            // Get the result of description
            price = i.getStringExtra("price");
            // Get the result of country
            price_unit = i.getStringExtra("price_unit");

            return null;

        }
    }
}
