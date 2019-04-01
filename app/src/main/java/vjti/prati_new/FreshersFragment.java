package vjti.prati_new;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import static android.text.Html.FROM_HTML_MODE_COMPACT;


public class FreshersFragment extends Fragment {

    private View fragment;
    private Button btn_freshers;
    private FragmentManager fragmentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_freshers, container, false);
        btn_freshers = (Button) fragment.findViewById(R.id.btn_freshers);
        fragmentManager = getFragmentManager();
        String s = getString(R.string.str_freshers);
        if (Build.VERSION.SDK_INT >= 24) {
            Html.fromHtml(s, FROM_HTML_MODE_COMPACT);
        } else {
            Html.fromHtml(s);
        }

        btn_freshers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckNetwork.isInternetAvailable(getActivity())) //returns true if internet available
                {

                    fragmentManager.beginTransaction().replace(R.id.content_frame, new BlankFragment()).commit();
                }
                else
                {
                    Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_LONG).show();
                }

            }
        });

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Aarambh");
    }

}

class CheckNetwork {


    private static final String TAG = CheckNetwork.class.getSimpleName();



    public static boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
            Log.d(TAG,"no internet connection");
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                Log.d(TAG," internet connection available...");
                return true;
            }
            else
            {
                Log.d(TAG," internet connection");
                return true;
            }

        }
    }
}
