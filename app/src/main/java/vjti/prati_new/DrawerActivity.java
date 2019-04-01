package vjti.prati_new;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


public class DrawerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private DrawerLayout drawer;
    private ListView lv_drawer;
    private TextView tv_nav_sign;
    private String[] sections = {"Home", "Theme", "Aarambh", "Illuminati", "Inter-collegiate", "Social Drives", "Past Sponsors", "Gallery", "Contact Us"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        lv_drawer = (ListView) findViewById(R.id.lv_drawer);
        lv_drawer.setAdapter(new drawerRow(getApplicationContext(), sections, 70, 60));
        lv_drawer.setOnItemClickListener(this);

//        tv_nav_sign = (TextView) findViewById(R.id.tv_nav_sign);
//        tv_nav_sign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragmentManager.beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();
//                toolbar.setTitle("Sign Up");
//                drawer.closeDrawers();
//            }
//        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) hView.findViewById(R.id.nav_header_img);
        Glide.with(getApplicationContext())
                .load(R.drawable.nav_prati)
                .into(imageView);

        fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_sign) {
            Fragment replace = new LoginFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame, replace).commit();
            toolbar.setTitle("Sign Up");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String item = lv_drawer.getItemAtPosition(i).toString();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment replace;

        if (item == "Home") {
            replace = new HomeFragment();
        } else if (item == "Theme") {
            replace = new AarambhFragment();
        } else if (item == "Aarambh") {
            replace = new FreshersFragment();
        } else if (item == "Illuminati") {
            replace = new IlluminatiFragment();
        } else if (item == "Inter-collegiate") {
            replace = new PAFragment();
        } else if (item == "Events") {
            replace = new EventsFragment();
        } else if (item == "Past Sponsors") {
            replace = new NewGallaryFragment();
        } else if (item == "Social Drives") {
            replace = new SocialFragment();
        } else if (item == "Gallery") {
            replace = new GalleryFragment();
        } else if (item == "Contact Us") {
            replace = new CommitteeFragment();
        }else {
            replace = new HomeFragment();
        }

        toolbar.setTitle(item);
        fragmentManager.beginTransaction().replace(R.id.content_frame, replace).addToBackStack(null).commit();
        drawer.closeDrawers();
    }
/*
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment replace = new HomeFragment();
        String tool_title = "Home";

        if (id == R.id.nav_home) {
            replace = new HomeFragment();
            tool_title = "Home";
        } else if (id == R.id.nav_events) {
            replace = new EventsFragment();
            tool_title = "Events";
        } else if (id == R.id.nav_reg) {
            replace = new LoginFragment();
            tool_title = "Sign Up";
        } else if (id == R.id.nav_pr) {

        } else if (id == R.id.nav_spons) {

        } else if (id == R.id.nav_committee) {

        }else {
            replace = new HomeFragment();
        }

        fragmentManager.beginTransaction().replace(R.id.content_frame, replace).commit();
        toolbar.setTitle(tool_title);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
*/
}

class drawerRow extends BaseAdapter {

    private String[] sections;
    private Context context;
    private int height;
    private int padding_left;

    drawerRow(Context c, String[] s, int h, int p){
        context = c;
        sections = s;
        height = h;
        padding_left = p;
    }

    @Override
    public int getCount() {
        return sections.length;
    }

    @Override
    public Object getItem(int i) {
        return sections[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.drawer_row, viewGroup, false);
        TextView t = (TextView) row.findViewById(R.id.tv_drawer_row);
        t.setText(sections[i]);
        t.setPadding(padding_left, 0, 0, 0);
        t.setHeight(height);
        return row;
    }
}
