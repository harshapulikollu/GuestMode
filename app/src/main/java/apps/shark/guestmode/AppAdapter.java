package apps.shark.guestmode;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Harsha on 1/31/2018.
 */

public class AppAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<AppList> listStorage;
    boolean[] itemChecked;
    String pckgName;
    private Context context;
    private PackageManager pm;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    boolean logval = false;

    public AppAdapter(Context context, List<AppList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
        itemChecked = new boolean[listStorage.size()];
        pref = context.getSharedPreferences("guestMode", Context.MODE_PRIVATE);
        editor = pref.edit();


    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.installed_app_list, parent, false);

            listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.list_app_name);
            listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.app_icon);
            listViewHolder.switchInListView = (Switch)convertView.findViewById(R.id.switchon);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.textInListView.setText(listStorage.get(position).getName());
        listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).getIcon());

       
        if(itemChecked[position] || logval){

            listViewHolder.switchInListView.setChecked(true);
        }
        else{
            listViewHolder.switchInListView.setChecked(false);
        }







        listViewHolder.switchInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listViewHolder.switchInListView.isChecked()){
                    itemChecked[position]= true;
                    editor.putBoolean(pckgName,true);
                    editor.commit();
                    /* pm = context.getApplicationContext().getPackageManager();
                    ComponentName componentName = new ComponentName(context,pckgName);
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP); */

                }
                else{
                    itemChecked[position]= false;
                    editor.putBoolean(pckgName,false);
                    editor.commit();
                  /*  pm = context.getApplicationContext().getPackageManager();
                    ComponentName componentName = new ComponentName(context,pckgName);
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP); */

                }
            }
        });

        return convertView;
    }



    static class ViewHolder{

        TextView textInListView;
        ImageView imageInListView;
        Switch switchInListView;
    }
}