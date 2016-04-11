package sainsburys.com.scraper;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // URL Address
    String url = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?" +
            "listView=true&" +
            "orderBy=FAVOURITES_FIRST&" +
            "parent_category_rn=12518&" +
            "top_category=12518&" +
            "langId=44&" +
            "beginIndex=0&" +
            "pageSize=20&" +
            "catalogId=10137&" +
            "searchTerm=&" +
            "categoryId=185749&" +
            "listId=&" +
            "storeId=10151&" +
            "promotionId=%23langId=44&" +
            "storeId=10151&" +
            "catalogId=10137&" +
            "categoryId=185749&" +
            "parent_category_rn=12518&" +
            "top_category=12518&" +
            "pageSize=20&" +
            "orderBy=FAVOURITES_FIRST&" +
            "searchTerm=&" +
            "beginIndex=0&" +
            "hideFilters=true";
    ProgressDialog mProgressDialog;

    Elements product_name;
    Elements pricePerUnit;
    Elements pricePerMeasure;
    private RelativeLayout.LayoutParams listViewParameters;
    private DisplayMetrics metrics;
    private ListView listView;

    static String NAME = "name";
    static String PRICE = "price";
    static String PRICE_UNIT = "price_unit";
    static String IMAGE = "image";

    ListViewAdapter adapter;
    ArrayList<HashMap<String, String>> arraylist;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new Retrieve().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Description AsyncTask
    private class Retrieve extends AsyncTask<Void, Void, Void> {
        String product_name_string;
        String pricePerUnit_string;
        String pricePerMeasure_string;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Sainsburys List");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
               /* Document document = Jsoup.connect(url)
                        .header("Accept-Encoding", "gzip, deflate")
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                        .maxBodySize(0)
                        .timeout(600000)
                        .get();*/


                Document document = Jsoup.connect(url)
                        .data("listView", "true")
                        .data("orderBy", "FAVOURITES_FIRST")
                        .data("parent_category_rn", "true")
                        .data("top_category", "true")
                        .data("langId", "44")
                        .data("beginIndex", "0")
                        .data("pageSize", "20")
                        .data("catalogId", "10137")
                        .data("searchTerm", "")
                        .data("categoryId", "185749")
                        .data("listId", "")
                        .data("storeId", "10151")
                        .data("promotionId", "%23")
                        .data("storeId", "10151")
                        .data("catalogId", "10137")
                        .data("categoryId", "185749")
                        .data("parent_category_rn", "12518")
                        .data("top_category", "12518")
                        .data("pageSize", "20")
                        .data("orderBy", "FAVOURITES_FIRST")
                        .data("searchTerm", "")
                        .data("beginIndex", "0")
                        .data("hideFilters", "true")
                        .userAgent("Mozilla/5.0")
                        .timeout(30000)
                        .get();

                arraylist = new ArrayList<HashMap<String, String>>();
//                JSONObject jsonmap = new JSONObject();
                HashMap<String, String> jsonmap = new HashMap<String, String>();
                Pattern p = Pattern.compile("'file'.*?'(.*?)'", Pattern.CASE_INSENSITIVE);



                // Using Elements to get the Meta data
                product_name = document.select("meta[name=productName]");
                pricePerUnit = document.select("meta[name=pricePerUnit]");
                pricePerMeasure= document.select("meta[name=pricePerMeasure]");

                for(Element item: product_name){


                    // Locate the content attribute
                    /*product_name_string = product_name.attr("content").toString();
                    pricePerUnit_string = pricePerUnit.attr("content");
                    pricePerMeasure_string = pricePerMeasure.attr("content");*/

                    jsonmap.put(product_name_string, product_name.attr("content").toString());
                    jsonmap.put(pricePerUnit_string, pricePerUnit.attr("content"));
                    jsonmap.put(pricePerMeasure_string, pricePerMeasure.attr("content"));
                }


                //insert into array
                arraylist.add(jsonmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
           /* // Set description into TextView
            TextView txtname_product = (TextView) findViewById(R.id.name_product);
            TextView txtprice_product = (TextView) findViewById(R.id.price_product);
            TextView txtprice_unit_product = (TextView) findViewById(R.id.price_unit_product);

            txtname_product.setText(product_name_string);
            txtprice_product.setText(pricePerUnit_string);
            txtprice_unit_product.setText(pricePerMeasure_string);*/

            ListView listview = (ListView) findViewById(R.id.product_list_view);
            listViewParameters = (RelativeLayout.LayoutParams) listview.getLayoutParams();
//            listViewParameters.width = metrics.widthPixels;
            listview.setLayoutParams(listViewParameters);

            // Locate the listview in listview_main.xml
            listView = (ListView) findViewById(R.id.product_list_view);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(MainActivity.this, arraylist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}
