package vjti.prati_new;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterFragment extends Fragment {

    private View fragment;
    private FragmentManager fragmentManager;
    private DatabaseReference mDatabase;
    private Button btn_reg;
    private String name;
    private DatabaseReference reg;
    private EditText et_reg_name;
    private EditText et_reg_college;
    private EditText et_reg_phone;
    private EditText et_reg_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_register, container, false);
        fragmentManager = getFragmentManager();
        Bundle bundle = this.getArguments();
        name = bundle.getString("event", "Register");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name);
        btn_reg = (Button) fragment.findViewById(R.id.btn_reg);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("pa").child(name);

        et_reg_name = (EditText) fragment.findViewById(R.id.et_reg_name);
        et_reg_college = (EditText) fragment.findViewById(R.id.et_reg_college);
        et_reg_phone = (EditText) fragment.findViewById(R.id.et_reg_phone);
        et_reg_email = (EditText) fragment.findViewById(R.id.et_reg_email);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Event name: " + name, Toast.LENGTH_LONG).show();
                String n = et_reg_name.getText().toString();
                String c = et_reg_college.getText().toString();
                String p = et_reg_phone.getText().toString();
                String e = et_reg_email.getText().toString();

                if(n.isEmpty() || c.isEmpty() || p.isEmpty() || e.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill all the details!", Toast.LENGTH_LONG).show();
                } else {
                    DatabaseReference temp = mDatabase;
                    temp.child("name").setValue(n);
                    temp.child("email").setValue(e);
                    temp.child("college").setValue(c);
                }
            }
        });

        return fragment;
    }

}
