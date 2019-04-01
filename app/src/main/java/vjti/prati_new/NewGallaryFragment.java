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
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;



public class NewGallaryFragment extends Fragment {

    private View fragment;
    private GridView grid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_new_gallary, container, false);
        grid = (GridView) fragment.findViewById(R.id.grid);
        grid.setAdapter(new CustomGrid(getActivity()));
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Sponsors");
    }

}

class spons_item {
    StorageReference reference;

    spons_item(StorageReference r){
        reference = r;
    }
}

class CustomGrid extends BaseAdapter {
    private Context context;
    private ArrayList<spons_item> list;
    private StorageReference storageReference;
    private StorageReference[] img;
    private int i = 0;

    public CustomGrid(Context c ) {
        context = c;
        storageReference = FirebaseStorage.getInstance().getReference().child("spons");
        img = new StorageReference[36];
        for(i=1; i<37; i++){
            img[i-1] = storageReference.child("spons" + Integer.toString(i) + ".png");
        }
        list = new ArrayList<spons_item>();
        for(int i=0; i<36; i++){
            list.add(new spons_item(img[i]));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.grid_single, parent, false);

        ImageView image = (ImageView) row.findViewById(R.id.grid_image);

        spons_item temp = list.get(position);

        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(temp.reference)
                .into(image);

        return row;
    }
}
