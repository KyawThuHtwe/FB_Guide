package com.cu.fbguide.Activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.cu.fbguide.Adapter.FBAdapter;
import com.cu.fbguide.Model.FB;
import com.cu.fbguide.R;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.myatminsoe.mdetect.MDetect;

public class MainActivity extends AppCompatActivity {

    ArrayList<FB> fbArrayList;
    RecyclerView recyclerView;
    FBAdapter fbAdapter;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadingData();
        MDetect.INSTANCE.init(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem search_item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setIconified(false);
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    loadingData();
                    recyclerView.setAdapter(fbAdapter);
                }else {
                    fbAdapter.getFilter().filter(s);
                }
                return true;
            }
        });

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadingData(){
        fbArrayList=new ArrayList<>();
        if(fbArrayList.size()>0){
            fbArrayList.clear();
        }
        fbArrayList.add(new FB("PW change ရန္","https://m.facebook.com/settings/security/password/"));
        fbArrayList.add(new FB("အလွဆင္ရန္","https://m.facebook.com/profile/edit/infotab/section/forms/?section=contact-info&cb=1548035094&ref=bookmarks"));
        fbArrayList.add(new FB("Nickname(နာမည္အပို)ထည့္ရန္","https://m.facebook.com/profile/edit/info/nicknames/?ref=bookmarks"));
        fbArrayList.add(new FB("Fri အပ္ထားၿပီး လက္မခံသူေတြ Undo လုပ္ရန္","https://m.facebook.com/friends/center/requests/outgoing/?fb_ref=ft&ref=bookmarks"));
        fbArrayList.add(new FB("မိမိမန္႔ထားေသာ comment အားလုံးၾကည့္ရန္ ၊ မႏွစ္သက္ရင္ ျပန္ဖ်က္ရန္","https://m.facebook.com/100015890196549/allactivity?log_filter=cluster_116&category_key=commentscluster&ref=bookmarks"));
        fbArrayList.add(new FB("မိမိၾကည့္ခဲ့တဲ့ Video ေတြ ျပန္ရွာရန္","https://m.facebook.com/100015890196549/allactivity?log_filter=videowatch&category_key=videowatch&ref=bookmarks"));
        fbArrayList.add(new FB("Fb Name ေျပာင္းရန္","https://m.facebook.com/settings/account/?name"));
        fbArrayList.add(new FB("Gmail အသစ္ေျပာင္းရန္","https://m.facebook.com/settings/email/add"));
        fbArrayList.add(new FB("Phone No အသစ္ေျပာင္းရန္","https://m.facebook.com/phoneacquire/?source=m_account"));
        fbArrayList.add(new FB("မိမိ Fb အား ယာယီပိတ္ထားရန္","https://m.facebook.com/deactivate/?source=account_settings_page"));
        fbArrayList.add(new FB("ဘာသာစကား ေရြးခ်ယ္ရန္/ေျပာင္းရန္","https://m.facebook.com/language.php"));
        fbArrayList.add(new FB("Fb password အသစ္ေျပာင္းရန္","https://m.facebook.com/settings/security/password/"));
        fbArrayList.add(new FB("Trusted Contacts(TC) ခံထားရန္","https://m.facebook.com/trusted_contacts/view/?gfid=AQB9boIRRGvN0K_i"));
        fbArrayList.add(new FB("မိမိအေကာင့္ ဘယ္သူခိုးဝင္လဲ...ၾကည့္ရန္","https://m.facebook.com/account_review/?section=sessions"));
        fbArrayList.add(new FB("မိမိရဲ႕ Post/Comment/Friends အား ၾကည့္ရန္","https://m.facebook.com/account_review/?state=overview"));
        fbArrayList.add(new FB("အေကာင့္ကို 2 Step ခံထားရန္","https://m.facebook.com/security/2fac/setup/choose/"));
        fbArrayList.add(new FB("App Password ယူရန္","https://m.facebook.com/new_sec_settings/app_passwords/"));
        fbArrayList.add(new FB("အေကာင့္ ခိုးဝင္လၽွင္သိရန္ သတိေပးခ်က္ယူမယ္","https://m.facebook.com/login_alerts/settings/?source=security_settings_page"));
        fbArrayList.add(new FB("Add Friends ပိတ္ရန္","https://m.facebook.com/privacy/touch/basic/?mds=%2Fprivacy%2Ftouch%2Fselector%2Fdialog%2F%3FsettingFBID%3D8787540733%26return_uri%3D%252Fprivacy%252Ftouch%252Fbasic%252F&mdf=1"));
        fbArrayList.add(new FB("Friends List ေဖ်ာက္ရန္","https://m.facebook.com/privacy/touch/basic/?mds=%2Fprivacy%2Ftouch%2Fselector%2Fdialog%2F%3FsettingFBID%3D8787365733%26return_uri%3D%252Fprivacy%252Ftouch%252Fbasic%252F&mdf=1"));
        fbArrayList.add(new FB("မိမိ Wall တြင္ ပို့လာေရးလို့မရေအာင္ ပိတ္ရန္","https://m.facebook.com/privacy/touch/timeline_and_tagging/?mds=%2Fprivacy%2Ftouch%2Ftimeline_and_tagging_selectors%2Fdialog%2F%3Fcancel_uri%3D%252Fprivacy%252Ftouch%252Ftimeline_and_tagging%252F%26fbid%3D10153940308610734&mdf=1"));
        fbArrayList.add(new FB("Tag post ပိတ္ရန္","https://m.facebook.com/privacy/touch/tags/review/?type=tag&gfid=AQB7o-THcAe4uS8X"));
        fbArrayList.add(new FB("Follow ဖြင့္ရန္-Publish အကုန္ထားေပးပါ။","https://m.facebook.com/settings/subscribe/"));
        fbArrayList.add(new FB("Fb မြာ Block ထားသူေတြကို ျပန္ၾကည့္ရန္","https://m.facebook.com/privacy/touch/block/"));
        fbArrayList.add(new FB("See first/Follow/Unfollow တေနရာတည္းၾကည့္ရန္","https://m.facebook.com/feed_preferences/home/"));
        fbArrayList.add(new FB("Video ေတြ auto ပြင့္ေနတာကို ပိတ္ရန္","https://m.facebook.com/settings/videos/"));
        fbArrayList.add(new FB("Fb acc အား အၿပီးဖ်က္ရန္","https://m.facebook.com/account/delete/?back_uri=https%3A%2F%2Fm.facebook.com%2Fprivacy%2F&account_screen&consent"));
        fbArrayList.add(new FB("Page အသစ္ေထာင္ရန္","https://m.facebook.com/pages/creation_flow/?step=name&cat_ref_page_id=0&ref_type=bookmark"));
        fbArrayList.add(new FB("Group အသစ္ဖြဲ႕စည္းရန္","https://m.facebook.com/groups/?category=groups&ref=bookmarks"));
        fbArrayList.add(new FB("ID တင္ရန္","https://m.facebook.com/help/contact/301294069923254?id=301294069923254"));
        fbAdapter=new FBAdapter(getApplicationContext(),fbArrayList);
        recyclerView.setAdapter(fbAdapter);
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_left_right);
        recyclerView.setLayoutAnimation(controller);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}