package com.myapplication;

/**
 * Created by Faizan Ahmad on 12/22/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class FavoriteListActivty extends Activity {

    public static final String ARG_ITEM_ID = "favorite_list";

    ListView favoriteList;
    SharedPreference sharedPreference;
    List<Product> favorites;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    Activity activity;

    ProductListAdapter productListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fvt_list);
        final TextView mTextView = (TextView) findViewById(R.id.title_text);
        mTextView.setText("Favorites");


        // Get favorite items from SharedPreferences.
        sharedPreference = new SharedPreference();
        favorites = sharedPreference.getFavorites(this);


        if (favorites == null) {
            showAlert(getResources().getString(R.string.no_favorites_items),
                    getResources().getString(R.string.no_favorites_msg));
        } else {

            if (favorites.size() == 0) {
                showAlert(
                        getResources().getString(R.string.no_favorites_items),
                        getResources().getString(R.string.no_favorites_msg));
            }

            favoriteList = (ListView) findViewById(R.id.list_product);
            if (favorites != null) {
                productListAdapter = new ProductListAdapter(this, favorites);
                favoriteList.setAdapter(productListAdapter);

                favoriteList.setOnItemClickListener(new OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View arg1,
                                            int position, long arg3) {

                    }
                });

                favoriteList
                        .setOnItemLongClickListener(new OnItemLongClickListener() {

                            @Override
                            public boolean onItemLongClick(
                                    AdapterView<?> parent, View view,
                                    int position, long id) {

                                ImageView button = (ImageView) findViewById(R.id.imgbtn_favorite);

                                String tag = button.getTag().toString();
                                if (tag.equalsIgnoreCase("grey")) {
                                    sharedPreference.addFavorite(FavoriteListActivty.this,
                                            favorites.get(position));
                                    Toast.makeText(FavoriteListActivty.this,getResources().getString(R.string.add_favr),
                                            Toast.LENGTH_SHORT).show();

                                    button.setTag("red");
                                    button.setImageResource(R.drawable.heart_red);
                                } else {
                                    sharedPreference.removeFavorite(FavoriteListActivty.this,
                                            favorites.get(position));
                                    button.setTag("grey");
                                    button.setImageResource(R.drawable.heart_grey);
                                    productListAdapter.remove(favorites
                                            .get(position));
                                    Toast.makeText(
                                            FavoriteListActivty.this,
                                            getResources().getString(
                                                    R.string.remove_favr),
                                            Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            }
                        });
            }
        }

    }

    public void showAlert(String title, String message) {
        if (activity != null && !activity.isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(activity)
                    .create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setCancelable(false);

            // setting OK Button
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // activity.finish();
                            getFragmentManager().popBackStackImmediate();
                        }
                    });
            alertDialog.show();
        }
    }
}