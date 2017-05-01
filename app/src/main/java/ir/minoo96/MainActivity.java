package ir.minoo96;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ronash.pushe.Pushe;
import ir.minoo96.API.Callbacks.RequestCallback;
import ir.minoo96.API.Callbacks.SearchCallback;
import ir.minoo96.API.Parser;
import ir.minoo96.API.Requests;
import ir.minoo96.Fragments.FragmentCandidates;
import ir.minoo96.Fragments.FragmentNews;
import ir.minoo96.Fragments.FragmentUpdates;
import ir.minoo96.Fragments.FragmentVote;
import ir.minoo96.Items.Candidate;
import ir.minoo96.Items.Post;
import ir.minoo96.Utility.SharedPreferenceHelper;
import ir.minoo96.Utility.Utilities;
import ir.minoo96.Utility.Variables;
import it.sephiroth.android.library.bottomnavigation.BadgeProvider;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setFontAttrId(R.attr.fontPath).build());

        final ViewGroup root = (ViewGroup) findViewById(R.id.CoordinatorLayout01);
        final CoordinatorLayout coordinatorLayout;
        if (root instanceof CoordinatorLayout) {
            coordinatorLayout = (CoordinatorLayout) root;
        } else {
            coordinatorLayout = null;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setTitle("انتخابات شورای شهر مینودشت 96");

        final int statusbarHeight = getStatusBarHeight();
        final boolean translucentStatus = hasTranslucentStatusBar();
        final boolean translucentNavigation = hasTranslucentNavigation();

        if (translucentStatus) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) root.getLayoutParams();
            params.topMargin = -statusbarHeight;

            params = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
            params.topMargin = statusbarHeight;
        }

        if (translucentNavigation) {
            final ViewPager viewPager = getViewPager();
            if (null != viewPager) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) viewPager.getLayoutParams();
                params.bottomMargin = -getNavigationBarHeight();
            }
        }

//        getViewPager().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getViewPager().setCurrentItem(3, false);
//            }
//        }, 1);

        initializeBottomNavigation(savedInstanceState);
        initializeUI(savedInstanceState);

        Variables.feeds = new ArrayList<>();
        Variables.candidates = new ArrayList<>();
        Variables.posts = new ArrayList<>();
        Variables.searchCandidates = new ArrayList<>();
        Variables.searchPosts = new ArrayList<>();

        new Thread(new Runnable() {
            public void run() {
                try {
                    SharedPreferenceHelper newsCahce = new SharedPreferenceHelper(getBaseContext(), "news");
                    String news = newsCahce.getString("news", "");
                    if (news.length() > 1)
                        Parser.parseFeeds(new JSONArray(news));

                    SharedPreferenceHelper cahce = new SharedPreferenceHelper(getBaseContext(), "cache");

                    String candidates = cahce.getString("candidates", "");
                    if (candidates.length() > 1)
                        Parser.parseCandidates(new JSONArray(candidates));

                    String posts = cahce.getString("posts", "");
                    if (posts.length() > 1)
                        Parser.parsePosts(new JSONArray(posts));

                    String user = cahce.getString("user", "");
                    if (posts.length() > 1)
                        Parser.parseUser(new JSONObject(user));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String model = "";
                try {
                    model = Build.MODEL;
                } catch (Exception ex) {

                }
                Requests.fetchUser(getBaseContext(), Utilities.getSerial(getBaseContext()), model, new RequestCallback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailed() {

                    }
                });
            }
        }).start();

        Pushe.initialize(this, false);
        Fresco.initialize(this);
    }

    protected void initializeBottomNavigation(final Bundle savedInstanceState) {
        if (null == savedInstanceState) {
            getBottomNavigation().setDefaultSelectedIndex(0);
            final BadgeProvider provider = getBottomNavigation().getBadgeProvider();
            provider.show(R.id.bbn_item4);
        }
    }

    protected void initializeUI(final Bundle savedInstanceState) {
//        AccountHeader headerResult = new AccountHeaderBuilder()
//                .withActivity(this)
//                .withHeaderBackground(R.mipmap.header)
//                .addProfiles(
//                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.mipmap.person_button))
//                )
//                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
//                    @Override
//                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
//                        return false;
//                    }
//                })
//                .build();

//        new DrawerBuilder()
//                .withActivity(this)
//                .withToolbar(toolbar)
//                //.withAccountHeader(headerResult)
//                .build();

//        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
//        if (null != floatingActionButton) {
//            floatingActionButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    final View root = findViewById(R.id.CoordinatorLayout01);
//                    Snackbar snackbar = Snackbar.make(root, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction(
//                                    "Action",
//                                    null
//                            );
//                    snackbar.show();
//                }
//            });
//
//            if (hasTranslucentNavigation()) {
//                final ViewGroup.LayoutParams params = floatingActionButton.getLayoutParams();
//                if (CoordinatorLayout.LayoutParams.class.isInstance(params)) {
//                    CoordinatorLayout.LayoutParams params1 = (CoordinatorLayout.LayoutParams) params;
//                    if (FloatingActionButtonBehavior.class.isInstance(params1.getBehavior())) {
//                        ((FloatingActionButtonBehavior) params1.getBehavior()).setNavigationBarHeight(getNavigationBarHeight());
//                    }
//                }
//            }
//        }

        final ViewPager viewPager = getViewPager();
        if (null != viewPager) {

            getBottomNavigation().setOnMenuChangedListener(new BottomNavigation.OnMenuChangedListener() {
                @Override
                public void onMenuChanged(final BottomNavigation parent) {

                    viewPager.setAdapter(new ViewPagerAdapter(MainActivity.this, parent.getMenuItemCount()));
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                        }

                        @Override
                        public void onPageSelected(final int position) {
                            if (getBottomNavigation().getSelectedIndex() != position) {
                                getBottomNavigation().setSelectedIndex(position, false);
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(final int state) {
                        }
                    });
                }
            });
        }
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("جستجو کاندیدا و پست ها ...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("در حال جستجو ...");
                mDialog.setCancelable(true);
                mDialog.show();

                Requests.fetchSearch(getBaseContext(), query, new SearchCallback() {
                    @Override
                    public void onSuccess(ArrayList<Candidate> candidates, ArrayList<Post> posts) {
                        mDialog.dismiss();

                        Variables.searchCandidates = candidates;
                        Variables.searchPosts = posts;

                        Intent i = new Intent(MainActivity.this, SearchResultActivity.class);
                        i.putExtra("query", query);
                        startActivity(i);
                    }

                    @Override
                    public void onFailed() {
                        mDialog.dismiss();
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void onMenuItemSelect(final int itemId, final int position, final boolean fromUser) {
        if (fromUser) {
            getBottomNavigation().getBadgeProvider().remove(itemId);
            if (null != getViewPager()) {
                getViewPager().setCurrentItem(position);
            }
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes final int itemId, final int position, final boolean fromUser) {
//        if (fromUser) {
//            final FragmentManager manager = getSupportFragmentManager();
//            FragmentUpdates fragment = (FragmentUpdates) manager.findFragmentById(R.id.fragment);
//            if (null != fragment) {
//                fragment.scrollToTop();
//            }
//        }
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final int mCount;

        public ViewPagerAdapter(final AppCompatActivity activity, int count) {
            super(activity.getSupportFragmentManager());
            this.mCount = count;
        }

        @Override
        public Fragment getItem(final int position) {
            switch (position) {
                case 0:
                    return new FragmentUpdates();
                case 1:
                    return new FragmentCandidates();
                case 2:
                    return new FragmentNews();
                case 3:
                    return new FragmentVote();
            }

            return null;
        }

        @Override
        public int getCount() {
            return mCount;
        }
    }

    @Override
    public void onBackPressed() {
        if (getBottomNavigation().getSelectedIndex() != 0)
            getViewPager().setCurrentItem(0);
        else
            super.onBackPressed();
    }
}
