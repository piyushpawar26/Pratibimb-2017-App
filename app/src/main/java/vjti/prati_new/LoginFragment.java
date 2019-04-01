package vjti.prati_new;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LoginFragment extends Fragment {

    private View fragment;
    private TextView tv_login_acc;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_login, container, false);
        tv_login_acc = (TextView) fragment.findViewById(R.id.tv_login_acc);
        fragmentManager = getFragmentManager();

        tv_login_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.content_frame, new RegisterFragment()).commit();
            }
        });

        return fragment;
    }

}
