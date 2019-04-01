package vjti.prati_new;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventsFragment extends Fragment {

    private View fragment;
    private TabLayout tab_layout;
    private ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_events, container, false);

        tab_layout = (TabLayout) fragment.findViewById(R.id.tab_layout);

        pager = (ViewPager) fragment.findViewById(R.id.pager);
        pager.setAdapter(new EventsAdapter(getChildFragmentManager()));
        tab_layout.setupWithViewPager(pager);

        return fragment;
    }

}


class EventsAdapter extends FragmentPagerAdapter {

    private final String[] titles = {"Day 1", "Day 2", "Day 3"};

    public EventsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 1){
            return new Day2Fragment();
        }else if(position == 2){
            return new Day3Fragment();
        }
        return new Day1Fragment();
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