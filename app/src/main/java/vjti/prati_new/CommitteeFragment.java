package vjti.prati_new;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CommitteeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View fragment;
    private ExpandableListView lv_comm;
    private List<String> listDataHeader;
    private HashMap<String, List<committeeRow>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_committee, container, false);
        lv_comm = (ExpandableListView) fragment.findViewById(R.id.lv_comm);
        prepareData();
        lv_comm.setAdapter(new committeeAdapter(getActivity(), listDataHeader, listDataChild));
        lv_comm.setOnItemClickListener(this);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Contact Us");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void prepareData(){
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        String[] comm_name = {"Secretaries", "Treasurers", "Executive Director", "Chief Executive Officers", "Chief Coordinators", "Performing Arts", "Mega event head", "Documentation and literary head"};
        // Adding child data
        listDataHeader.add("Secretaries");
        listDataHeader.add("Treasurers");
        listDataHeader.add("Executive Director");
        listDataHeader.add("Chief Executive Officers");
        listDataHeader.add("Chief Coordinators");
        listDataHeader.add("Performing Arts");
        listDataHeader.add("Mega event head");
        listDataHeader.add("Documentation and literary head");

        // Adding child data
        List<committeeRow> seccretaries = new ArrayList<committeeRow>();
        seccretaries.add(new committeeRow("Janhavi Savla", "+919869567771"));
        seccretaries.add(new committeeRow("Aditi Kagane", "+918177832120"));
        seccretaries.add(new committeeRow("Nisarg Tole", "+918080672612"));

        List<committeeRow> treasures = new ArrayList<committeeRow>();
        treasures.add(new committeeRow("Harsh Kokane", "+917045665334"));
        treasures.add(new committeeRow("Harshal Dongre", "+919665389528"));


        List<committeeRow> exe_dir = new ArrayList<committeeRow>();
        exe_dir.add(new committeeRow("Jatin Jain", "+919167933001"));

        List<committeeRow> cheif_exe_dir = new ArrayList<committeeRow>();
        cheif_exe_dir.add(new committeeRow("Kartikeya Rathod", "+917021667926"));
        cheif_exe_dir.add(new committeeRow("Shalin Shah", "+917506073220"));

        List<committeeRow> cheif_cor = new ArrayList<committeeRow>();
        cheif_cor.add(new committeeRow("Krishna Rathi\n(Sponsorship)", "+917219420290"));
        cheif_cor.add(new committeeRow("Kaustubh Shirpurkar\n(Public Relations)", "+917666914540"));
        cheif_cor.add(new committeeRow("Manognya Wunnava\n(Marketing & Design)", "+917506045377"));
        cheif_cor.add(new committeeRow("Rohit Pawar\n(Operations)", "+918689916987"));
        cheif_cor.add(new committeeRow("Prajwal Khade\n(Illuminati)", "+919423805321"));

        List<committeeRow> pa = new ArrayList<committeeRow>();
        pa.add(new committeeRow("Shraddha Singh\n(Secretary)", "+919833502247"));
        pa.add(new committeeRow("Shraddha Datta\n(Joint Secretary)", "+919004726554"));

        List<committeeRow> mega = new ArrayList<committeeRow>();
        mega.add(new committeeRow("Khushwant Patil", "+917276466908"));

        List<committeeRow> doc = new ArrayList<committeeRow>();
        doc.add(new committeeRow("Shoeb Memon", "+919699645786"));

        listDataChild.put(listDataHeader.get(0), seccretaries); // Header, Child data
        listDataChild.put(listDataHeader.get(1), treasures);
        listDataChild.put(listDataHeader.get(2), exe_dir);
        listDataChild.put(listDataHeader.get(3), cheif_exe_dir);
        listDataChild.put(listDataHeader.get(4), cheif_cor);
        listDataChild.put(listDataHeader.get(5), pa);
        listDataChild.put(listDataHeader.get(6), mega);
        listDataChild.put(listDataHeader.get(7), doc);
    }
}

class committeeRow {

    String row_comm_email;
    String row_comm_phone;

    committeeRow(String e, String p){
        row_comm_email = e;
        row_comm_phone = p;
    }

}

class committeeAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<committeeRow>> listDataChild;

    committeeAdapter(Context c, List<String> lh, HashMap<String, List<committeeRow>> ld){
        context = c;
        listDataHeader = lh;
        listDataChild = ld;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listDataChild.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listDataChild.get(listDataHeader.get(i));
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.committee_row, null);
        }

        TextView lblListHeader = (TextView) view.findViewById(R.id.comm_row_head);
        lblListHeader.setText(headerTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        committeeRow t = listDataChild.get(listDataHeader.get(i)).get(i1);
        String p = t.row_comm_phone;
        String e = t.row_comm_email;

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.committee_expanded_row, null);
        }

        TextView phone = (TextView) view.findViewById(R.id.comm_row_phone);
        TextView mail = (TextView) view.findViewById(R.id.comm_row_mail);
        phone.setText(p);
        mail.setText(e);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
