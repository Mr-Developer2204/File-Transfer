package com.example.filetransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    GridView gridView;

    int[] iconList = new int[]{
            R.drawable.facebook, R.drawable.twitter, R.drawable.instagram, R.drawable.snapchat,
            R.drawable.whatsapp, R.drawable.line, R.drawable.kakaotalk, R.drawable.telegram, R.drawable.messenger,
            R.drawable.youtube, R.drawable.tiktok, R.drawable.vine, R.drawable.vimeo,
            R.drawable.figma, R.drawable.adobe_xd, R.drawable.sketch, R.drawable.framer,
            R.drawable.android, R.drawable.apple, R.drawable.windows,
            R.drawable.opera, R.drawable.firefox, R.drawable.safari, R.drawable.edge,
            R.drawable.paypal, R.drawable.mastercard, R.drawable.visa,
            R.drawable.bitcoin, R.drawable.ethereum
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);

        gridView = findViewById(R.id.grid_view);

        gridView.setAdapter(new ImageGridViewAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getBaseContext(),
                        "Grid Item " + (position + 1) + " Selected",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    public class ImageGridViewAdapter extends BaseAdapter {
        private Context mContext;

        ImageGridViewAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return iconList.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView mImageView;

            if (convertView == null) {
                mImageView = new ImageView(mContext);
                mImageView.setLayoutParams(new GridView.LayoutParams(200, 200));
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mImageView.setPadding(16, 16, 16, 16);
            } else {
                mImageView = (ImageView) convertView;
            }
            mImageView.setImageResource(iconList[position]);
            return mImageView;
        }
    }
}


