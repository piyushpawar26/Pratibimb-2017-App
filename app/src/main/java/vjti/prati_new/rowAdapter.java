package vjti.prati_new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class rowAdapter extends BaseAdapter {

    String[] titles;
    Context context;

    rowAdapter(Context c, String[] a){
        context = c;
        titles = a;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.events_list_row, viewGroup, false);
        TextView t = (TextView) row.findViewById(R.id.row_title);
        t.setText(titles[i]);
        return row;
    }

}
