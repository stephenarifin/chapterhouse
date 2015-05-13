package arifin.chapterhouse;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {

    private String[] mNavigationTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private static String xrsf_token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup navigation drawer
        mNavigationTitles = getResources().getStringArray(R.array.navigation_titles);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the ListView
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.navigation_list_item,
                mNavigationTitles));

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());



        // Fragment transactions

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentChapter fragment = new FragmentChapter();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.members, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;

            switch(position){
                case 0: // Chapter
                    fragment = new FragmentChapter();
                    break;
                case 1: // Forums
                    fragment = new FragmentForums();
                    break;
                case 2: // Events
                    fragment = new FragmentEvents();
                    break;
                case 3: // Members
                    fragment = new FragmentMembers();
                    break;
                case 4: // Payments
                    fragment = new FragmentPayments();
                    break;
            }

            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }
    }

    public static gete

}
