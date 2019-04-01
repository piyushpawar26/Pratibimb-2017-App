package vjti.prati_new;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.text.Html.FROM_HTML_MODE_COMPACT;

public class AarambhFragment extends Fragment {

    private View fragment;
    private ViewPager pager;
    private GalleryAdapter myAdapter;
    private int page = 0;
    private Handler handler;
    private int delay = 3000;
    private Runnable update;
    private StorageReference storageReference;
    private StorageReference[] img;
    private TextView tv_aarambh_two;
    private ImageView iv_aarambh;
//    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_aarambh, container, false);
        handler = new Handler();
        storageReference = FirebaseStorage.getInstance().getReference().child("aarambh");
        img = new StorageReference[8];
        iv_aarambh = (ImageView) fragment.findViewById(R.id.iv_aarambh);
        Glide.with(getActivity())
                .using(new FirebaseImageLoader())
                .load(storageReference.child("image2.jpg" +
                        ""))
                .into(iv_aarambh);
        tv_aarambh_two = (TextView) fragment.findViewById(R.id.tv_aarambh_two);
        String s = getString(R.string.str_aarambh_two);
        if (Build.VERSION.SDK_INT >= 24) {
            Html.fromHtml(s, FROM_HTML_MODE_COMPACT);
        } else {
            Html.fromHtml(s);
        }
//        for(int i=2; i<10; i++) {
//            img[i - 2] = storageReference.child("image" + Integer.toString(i) + ".jpg");
//        }
//        myAdapter = new GalleryAdapter(getActivity(), img);
//        pager = (ViewPager) fragment.findViewById(R.id.pager_aarambh);
//        pager.setAdapter(myAdapter);
//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                page = position+1;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        update = new Runnable() {
//            public void run() {
//                if (myAdapter.getCount() == page) {
//                    page = 0;
//                }
//                pager.setCurrentItem(page++, true);
//            }
//        };
//
//
//        new Timer().schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                handler.post(update);
//            }
//        }, delay, delay);

        return fragment;
    }

}


