package mohkarmon.a4moc.lbj.Screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mohkarmon.a4moc.lbj.R;

public class Connected extends AppCompatActivity {
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment =  new BrowseAds();
                    getSupportFragmentManager().beginTransaction().replace(R.id.navFrame, selectedFragment).commit();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = new BookmarkedAds();

                    getSupportFragmentManager().beginTransaction().replace(R.id.navFrame, selectedFragment).commit();
                    break;
                case R.id.navigation_notifications:
                    selectedFragment =  new Profile();
                    getSupportFragmentManager().beginTransaction().replace(R.id.navFrame, selectedFragment).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);


    }

}
