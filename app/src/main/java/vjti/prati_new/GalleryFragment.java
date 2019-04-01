package vjti.prati_new;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private View fragment;
    private LinearLayout ll_gallery;
    private ViewPager pager;
    private StorageReference storageReference;
    private StorageReference[] img;
//    private int[] images = {R.drawable.image1, R.drawable.image4, R.drawable.wallp1, R.drawable.trek3};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_gallery, container, false);
        ll_gallery = (LinearLayout) fragment.findViewById(R.id.ll_gallery);
        pager = (ViewPager) fragment.findViewById(R.id.pager_gallery);

        storageReference = FirebaseStorage.getInstance().getReference().child("gallery");
        img = new StorageReference[30];

        for(int i=1; i<31; i++) {
            img[i - 1] = storageReference.child("image" + Integer.toString(i) + ".jpg");
        }

        pager.setAdapter(new GalleryAdapter(getActivity(), img));

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;

        int imgHeight =  (int) (screenHeight * 0.18);
        int imgWidth =  (int) (screenWidth * 0.30);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imgWidth, imgHeight);
        layoutParams.setMargins(0, 0, 10, 0);

        for(int i=0; i<img.length; i++){
            ImageView image = new ImageView(getActivity());
//            image.setBackgroundResource(images[i]);
            Glide.with(getActivity())
                    .using(new FirebaseImageLoader())
                    .load(img[i])
                    .into(image);
            image.setId(i);
            image.setOnClickListener(this);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ll_gallery.addView(image, layoutParams);
        }

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Gallery");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        pager.setCurrentItem(id);
    }
}

class GalleryAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private StorageReference[] images;

    GalleryAdapter(Context c, StorageReference[] i){
        context = c;
        images = i;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.gallery_pager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_gallery_pager);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(images[position])
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
