package vjti.prati_new;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    private View fragment;
    private ViewPager pager;
    private GalleryAdapter myAdapter;
    private int page = 0;
    private Handler handler;
    private int delay = 3000;
    private Runnable update;
    private StorageReference storageReference;
    private StorageReference[] img;
//    private int[] images = {R.drawable.home1, R.drawable.home2, R.drawable.home3};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragment = inflater.inflate(R.layout.new_home_layout, container, false);
        handler = new Handler();

        ImageView imageView = (ImageView) fragment.findViewById(R.id.iv_vjti);
        Glide.with(getActivity())
                .load(R.drawable.vjti)
                .into(imageView);

        storageReference = FirebaseStorage.getInstance().getReference().child("home");
        img = new StorageReference[5];
        for(int i=1; i<6; i++) {
            img[i - 1] = storageReference.child("image" + Integer.toString(i) + ".jpg");
        }

        myAdapter = new GalleryAdapter(getActivity(), img);
        pager = (ViewPager) fragment.findViewById(R.id.pager_home);
        pager.setAdapter(myAdapter);
        //pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position+1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        update = new Runnable() {
            public void run() {
                if (myAdapter.getCount() == page) {
                    page = 0;
                }
                pager.setCurrentItem(page++, true);
            }
        };


        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, delay, delay);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Home");
    }

}
/*
class myHomeAdapter extends FragmentPagerAdapter {

    private final String[] titles = {"one", "two", "three"};

    myHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 1){
            return new Home2Fragment();
        } else if(position == 2){
            return new Home3Fragment();
        }
        return new Home1Fragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) {
            view.setAlpha(0);

        } else if (position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else {
            view.setAlpha(0);
        }
    }

}*/