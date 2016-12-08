package ssjk.cafein;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import kankan.wheel.widget.adapters.AbstractWheelAdapter;

/**
 * Created by wqe13 on 2016-12-06.
 */

class RandomAdapter extends AbstractWheelAdapter {
    String items[];
    // Image size
    final private int SLOT_WIDTH = 300;
    final private int SLOT_HEIGHT = 60;


    private void initarray(String strtext) {
        int Count;
        Cursor cur;
        DatabaseOpenHelper database = new DatabaseOpenHelper();
        if (strtext.equals("CAFE")) {
            String s = "SELECT * FROM TABLE_SUGGESTION WHERE FIELD_TYPE = 'CAFE'";
            cur = database.searchDatabase(s);
            Count = cur.getCount();
            items = new String[Count];
            cur.moveToFirst();
            for (int i = 0; i < Count; i++, cur.moveToNext()) {
                items[i] = cur.getString(1);
            }
        }else {
            database = new DatabaseOpenHelper();
            String s = String.format("SELECT * FROM Cafein WHERE CAFE_NAME = '%s'", strtext);
            cur = database.searchDatabase(s);
            Count = cur.getCount();
            Count = Count - 1;
            items = new String[Count];
            cur.moveToFirst();
            cur.moveToNext();
            for (int i = 0; i < Count; i++, cur.moveToNext()) {
                items[i] = cur.getString(3);
            }
        }
        cur.close();
    }

    // Cached images
    private List<SoftReference<String>> lists;

    // Layout inflater
    private Context context;

    /**
     * Constructor
     */
    RandomAdapter(Context context, String str) {
        initarray(str);
        this.context = context;
        lists = new ArrayList<SoftReference<String>>(items.length);
        for (String sid : items) {
            lists.add(new SoftReference<String>(sid));
        }
    }

    @Override
    public int getItemsCount() {
        return items.length;
    }

    // Layout params for image view
    final private ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(SLOT_WIDTH, SLOT_HEIGHT);

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        TextView txt;
        if (cachedView != null) {
            txt = (TextView) cachedView;
        } else {
            txt = new TextView(context);
        }
        txt.setLayoutParams(params);
        SoftReference<String> strRef = lists.get(index);
        String str = strRef.get();
        if (str == null) {
            str = items[index];
            lists.set(index, new SoftReference<String>(str));
        }
        txt.setText(str);
        return txt;
    }
}

