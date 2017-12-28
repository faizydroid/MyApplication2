package com.myapplication;

/**
 * Created by Faizan Ahmad on 12/22/2017.
 */

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductListTwo extends Activity implements
        OnItemClickListener, OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "product_list";
    private ClipboardManager myClipboard;
    private ClipData myClip;
    Activity activity;
    ListView productListView;
    List<Product> products;
    String[] web ;
    ProductListAdapter productListAdapter;

    SharedPreference sharedPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);
        productListView = (ListView) findViewById(R.id.list_product);
        sharedPreference = new SharedPreference();
        final TextView mTextView = (TextView) findViewById(R.id.title_text);
        mTextView.setText("List 2");


        setProducts();

        productListAdapter = new ProductListAdapter(this, products);

        productListView.setAdapter(productListAdapter);
        productListView.setOnItemClickListener(this);
        productListView.setOnItemLongClickListener(this);

    }

    private void setProducts() {


        products = new ArrayList<Product>();
        String[] web = getResources().getStringArray(R.array.s_cool);
        for (int i = 0; i < web.length; i++) {
            Product product = new Product(i,web[i]);
            products.add(product);
        }
    }





    @Override
    public void onItemClick(AdapterView<?> arg0, View view,
                            int position, long arg3) {

    }
    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                   int position, long arg3) {
        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);

        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase("grey")) {
            sharedPreference.addFavorite(this, products.get(position));
            Toast.makeText(this,
                    getResources().getString(R.string.add_favr),
                    Toast.LENGTH_SHORT).show();

            button.setTag("red");
            button.setImageResource(R.drawable.heart_red);
        } else {
            sharedPreference.removeFavorite(this, products.get(position));
            button.setTag("grey");
            button.setImageResource(R.drawable.heart_grey);
            Toast.makeText(this,
                    getResources().getString(R.string.remove_favr),
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }

}