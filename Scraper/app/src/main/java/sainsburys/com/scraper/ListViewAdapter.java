package sainsburys.com.scraper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alessio on 09/04/2016.
 */
public class ListViewAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<HashMap<String, String>> data;
    private LayoutInflater inflater;
    private TextView name;
    private TextView price;
    private TextView price_unit;
    private ImageView image;
    ImageLoader imageLoader;


    public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.single_item_view, parent, false);
        // Get the position from the results
        HashMap<String, String> resultp = new HashMap<String, String>();
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        name = (TextView) itemView.findViewById(R.id.name_product);
        price = (TextView) itemView.findViewById(R.id.price_product);
        price_unit = (TextView) itemView.findViewById(R.id.price_unit_product);

        // Locate the ImageView in listview_item.xml
        image = (ImageView) itemView.findViewById(R.id.product_image);


        // Capture position and set results to the TextViews
        name.setText(resultp.get(MainActivity.NAME));
        price.setText(resultp.get(MainActivity.PRICE));
        price_unit.setText(resultp.get(MainActivity.PRICE_UNIT));
        /*Capture position and set results to the ImageView
        passes image URL into ImageLoader.class to download and cache images*/
        imageLoader.DisplayImage(resultp.get(MainActivity.IMAGE), image);

        // Capture button clicks on ListView items
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position from the results
                HashMap<String, String> resultp = new HashMap<String, String>();
                resultp = data.get(position);
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data title
                intent.putExtra("name", resultp.get(MainActivity.NAME));
                // Pass all data description
                intent.putExtra("price", resultp.get(MainActivity.PRICE));
                // Pass all data country
                intent.putExtra("price_unit", resultp.get(MainActivity.PRICE_UNIT));
                // Pass all data image
                intent.putExtra("image", resultp.get(MainActivity.IMAGE));

                //start single item class
                context.startActivity(intent);
            }
        });
        return itemView;

    }

}