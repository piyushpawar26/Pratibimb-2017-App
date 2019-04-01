package vjti.prati_new;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;


public class Day3Fragment extends Fragment {


    private View fragment;
    private ListView listView;
    private ArrayList<illuminatiRow> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_day3, container, false);
        listView = (ListView) fragment.findViewById(R.id.lv_events_day3);
        prepareData();
        listView.setAdapter(new illuminatiAdapter(getActivity(), list));
        return fragment;
    }

    public void prepareData(){
        list = new ArrayList<>();
        String[] name = {"Prati 3", "Event Name 2", "Event Name 3", "Event Name 4", "Event Name 5", "Event Name 6"};
        String[] date = {"20 Dec @ 5PM", "20 Dec @ 5PM", "20 Dec @ 5PM", "20 Dec @ 5PM", "20 Dec @ 5PM", "20 Dec @ 5PM"};
        String[] venue = {"Quad", "Quad", "Quad", "Quad", "Quad", "Quad"};
        int[] text = {R.string.sample, R.string.sample, R.string.sample, R.string.sample, R.string.sample, R.string.sample};
        for(int i=0; i<name.length; i++){
            illuminatiRow temp = new illuminatiRow(name[i], text[i]);
            list.add(temp);
        }
    }

}
