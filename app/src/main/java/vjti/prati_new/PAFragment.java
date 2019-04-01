package vjti.prati_new;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class PAFragment extends Fragment {

    private View fragment;
    private ListView lv_pa;
    private FragmentManager fragmentManager;
    private String[] names = {"Classical Solo Singing", "Western Solo Singing", "Solo Dancing", "Duet Dancing", "Monoacting", "Symphony", "Rap Wars", "Beat Boxing", "Band Wars", "Stand Up Comedy", "Freshface", "Group Dance", "Mumbai's Got Talent"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_pa, container, false);
        fragmentManager = getFragmentManager();

        lv_pa = (ListView) fragment.findViewById(R.id.lv_pa);
        lv_pa.setAdapter(new PAAdapter(getActivity(), names, fragmentManager));
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Inter-collegiate");
    }

}

class PAAdapter extends BaseAdapter {

    private String[] names;
    private Context context;
    private FragmentManager fragmentManager;

    PAAdapter(Context c, String[] n, FragmentManager f) {
        context = c;
        names = n;
        fragmentManager = f;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return names[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.pa_row, null);
        }
        TextView name = (TextView) view.findViewById(R.id.tv_pa_name);
        TextView reg = (TextView) view.findViewById(R.id.tv_pa_reg);
        name.setText(names[i]);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Fragment fragment = new RegisterFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("event", names[i]);
//                fragment.setArguments(bundle);
//                fragmentManager.beginTransaction().replace(R.id.content_frame, new BlankFragment()).commit();
                Toast.makeText(context, "Wait For It!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
