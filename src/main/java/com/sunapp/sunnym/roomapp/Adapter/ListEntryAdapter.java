package com.sunapp.sunnym.roomapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunapp.sunnym.roomapp.Model.EntryInfo;
import com.sunapp.sunnym.roomapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by SunnyM on 10/22/2015.
 */

public class ListEntryAdapter extends BaseAdapter{

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList<EntryInfo> data;
    private static LayoutInflater inflater=null;
    public Resources res;
    private EntryInfo objEntryInfo;

    /*************  CustomAdapter Constructor *****************/
    public ListEntryAdapter(Activity a, ArrayList<EntryInfo> d) {

        /********** Take passed values **********/
        activity = a;
        data=d;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView txtShare;
        public TextView txtDate;
        public TextView txtItem;
        public TextView txtPrice;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.listitem, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.txtPrice =(TextView)vi.findViewById(R.id.txtPrice);
            holder.txtDate = (TextView) vi.findViewById(R.id.txtDate);
            holder.txtItem=(TextView)vi.findViewById(R.id.txtItem);
            holder.txtShare = (TextView) vi.findViewById(R.id.txtShare);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.txtDate.setText("No Data");
            holder.txtPrice.setText("");
            holder.txtItem.setText("");
            holder.txtShare.setText("");

        }
        else
        {
            objEntryInfo = ( EntryInfo ) data.get( position );

            /************  Set Model values in Holder elements ***********/

            holder.txtDate.setText( objEntryInfo.getDate());
            holder.txtItem.setText(objEntryInfo.getItemName());
            holder.txtPrice.setText(objEntryInfo.getTotalPrice());
            holder.txtShare.setText(objEntryInfo.getShare());

        }
        return vi;
    }
}
