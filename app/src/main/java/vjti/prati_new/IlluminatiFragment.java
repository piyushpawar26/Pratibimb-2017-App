package vjti.prati_new;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class IlluminatiFragment extends Fragment {

    private View fragment;
    private ListView lv_illuminati;
    private ArrayList<illuminatiRow> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_illuminati, container, false);
        lv_illuminati = (ListView) fragment.findViewById(R.id.lv_illuminati);
        prepareData();
        lv_illuminati.setAdapter(new illuminatiAdapter(getActivity(), list));
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Illuminati");
    }

    public void prepareData(){
        list = new ArrayList<>();
        String[] name = {"Meme", "Parody", "JAG", "Director's cut", "Re-incarnation"};
        int[] text = {R.string.str_meme_event, R.string.str_parody_event, R.string.str_jag_event, R.string.str_directors_event, R.string.str_reincarnation_event};
        for(int i=0; i<name.length; i++){
            illuminatiRow temp = new illuminatiRow(name[i], text[i]);
            list.add(temp);
        }
    }

}

class illuminatiRow {
    String name;
    int text;

    illuminatiRow(String n, int t){
        name = n;
        text = t;
    }
}

class illuminatiAdapter extends BaseAdapter {

    private Context context;
    private List<illuminatiRow> list;

    illuminatiAdapter(Context c, ArrayList<illuminatiRow> l){
        context = c;
        list = l;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        illuminatiRow o = list.get(i);
        String n = o.name;
        int t = o.text;

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.illuminati_row, null);
        }

        TextView name = (TextView) view.findViewById(R.id.tv_ill_name);
        TextView text = (TextView) view.findViewById(R.id.tv_ill_text);

        name.setText(n);
        text.setText(t);
        return view;
    }
}
