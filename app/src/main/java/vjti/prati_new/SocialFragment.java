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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class SocialFragment extends Fragment {

    private View fragment;
    private ListView lv_social;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_social, container, false);
        lv_social = (ListView) fragment.findViewById(R.id.lv_social);
        lv_social.setAdapter(new socialAdapter(getActivity()));
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Social Drives");
    }

}

class socialRow{
    String title_soc;
    StorageReference image_soc;
    int desc_soc;

    socialRow(String title, int desc, StorageReference image){
        desc_soc = desc;
        image_soc = image;
        title_soc = title;
    }

}

class socialAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<socialRow> list;
    private String[] titles = {"Tree Plantation", "Khushi", "Awaaz", "Get-Set-Clean", "Udaan", "Railway Station Painting"};
    private int[] desc = {R.string.str_social_tree, R.string.str_social_khushi, R.string.str_social_awaaz, R.string.str_social_clean, R.string.str_social_udaan, R.string.str_social_painting};
    private StorageReference storageReference;
    private StorageReference[] img;
    private int i = 0;
//    private int[] img = {R.drawable.tree, R.drawable.khushi, R.drawable.awaaz, R.drawable.clean, R.drawable.tree, R.drawable.painting};

    socialAdapter(Context c){
        context = c;
        storageReference = FirebaseStorage.getInstance().getReference().child("social");
        img = new StorageReference[6];
        for(i=1; i<7; i++){
            img[i-1] = storageReference.child("social" + Integer.toString(i) + ".jpg");
//            storageReference.child("social" + Integer.toString(i) + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    img[i-1] = uri.toString();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    Toast.makeText(context, "image not dowloaded", Toast.LENGTH_SHORT).show();
//                }
//            });;
//            img[i-1] = String.valueOf(storageReference.child("social" + Integer.toString(i) + ".jpg").getDownloadUrl());
        }
        list = new ArrayList<socialRow>();
        for(int i=0; i<6; i++){
            list.add(new socialRow(titles[i], desc[i], img[i]));
        }
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.social_row, viewGroup, false);

        TextView title = (TextView) row.findViewById(R.id.social_row_title);
        ImageView image = (ImageView) row.findViewById(R.id.social_row_image);
        TextView desc = (TextView) row.findViewById(R.id.social_row_desc);

        socialRow temp = list.get(i);

        title.setText(temp.title_soc);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(temp.image_soc)
                .into(image);
        desc.setText(temp.desc_soc);

        return row;
    }
}
