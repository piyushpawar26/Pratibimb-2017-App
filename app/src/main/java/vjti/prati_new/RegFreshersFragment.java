package vjti.prati_new;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class RegFreshersFragment extends Fragment {

    private View fragment;
    private Spinner spinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_reg_freshers, container, false);

        spinner = (Spinner) fragment.findViewById(R.id.branch_spinner);

        String[] items = new String[]{"Computers", "Information Technology", "Electrical", "Electronics", "EXTC", "Civil", "Mechanical", "Production", "Textile", "Chemsa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        return fragment;
    }

}
