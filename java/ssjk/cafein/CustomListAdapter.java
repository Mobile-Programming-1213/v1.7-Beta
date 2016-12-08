package ssjk.cafein;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListAdapter extends BaseAdapter {

    // ArrayList
    private ArrayList<String>   m_List;
    private ArrayList<String> m_Type;
    private ArrayList<String> m_eName;

    // creating a new adapter
    public CustomListAdapter() {
        m_List = new ArrayList<String>();
        m_Type = new ArrayList<String>();
        m_eName = new ArrayList<String>();
    }

    // return size of current array
    @Override
    public int getCount() {
        return m_List.size();
    }

    // returns item
    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if ( convertView == null ) {

            //custom layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom, parent, false);

            TextView text = (TextView) convertView.findViewById(R.id.text);
            text.setText(m_List.get(position).toLowerCase());
            Log.i("------------",  m_Type.get(position));
            ImageView type = (ImageView) convertView.findViewById(R.id.type);
            if (m_Type.get(position).equals("CAFE")){
                type.setImageResource(R.drawable.ic_image_cafe);
            } else {
                type.setImageResource(R.drawable.ic_image_beverage);
                TextView dtype = (TextView) convertView.findViewById(R.id.dtype);
                dtype.setText(m_Type.get(position).toLowerCase());
            }
            // Button in the listview
  /*          Button btn = (Button) convertView.findViewById(R.id.btn_test);
            btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                }
            });
*/
            // on listview touch
            convertView.setOnClickListener(new OnClickListener() {
                Intent intent;

                @Override
                public void onClick(View v) {
                if (m_Type.get(pos).equals("CAFE")) {
                    intent = new Intent(context, CafeInformation.class);
                    intent.putExtra("CafeName", m_List.get(pos));
                    intent.putExtra("ENGname", m_eName.get(pos));
                }
                else{
                    intent = new Intent(context, CoffeeList.class);
                    intent.putExtra("CoffeeName", m_List.get(pos));
                    intent.putExtra("DrinkType", m_Type.get(pos));
                }
                context.startActivity(intent);
            }
            });

            // on long click
 /*           convertView.setOnLongClickListener(new OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context, "리스트 롱 클릭 : "+m_List.get(pos), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });*/
        }

        return convertView;
    }

    public void add(String _msg, String type, String eName) {
        m_List.add(_msg);
        m_Type.add(type);
        m_eName.add(eName);
    }

    public void remove(int _position) {
        m_List.remove(_position);
    }
}