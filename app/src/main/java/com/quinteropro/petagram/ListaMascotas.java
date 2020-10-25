package com.quinteropro.petagram;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.quinteropro.petagram.adapter.PageAdapter;
import com.quinteropro.petagram.fragments.MascotaFragment;
import com.quinteropro.petagram.fragments.RecyclerViewFragment;
import com.quinteropro.petagram.menu.Acercade;
import com.quinteropro.petagram.menu.Contacto;
import com.quinteropro.petagram.pojo.Mascota;


public class ListaMascotas extends AppCompatActivity implements RecyclerViewFragment.RecyclerViewFragmentListener, MascotaFragment.MascotaFragmentListener {

     private Toolbar mtoolbar;
     private TabLayout mTabLayout;
     private ViewPager mviewPager;

     RecyclerViewFragment mRecyclerViewFragment;
     MascotaFragment mMascotaFragment;


    ArrayList<Mascota> mascotasList;
    ArrayList<Mascota> mascotasListTop5;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TASK_LIST = "taskList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);

        mtoolbar = (Toolbar) findViewById(R.id.miActionBarlogin);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mviewPager = (ViewPager) findViewById(R.id.viewPager);

        loadData();

        mRecyclerViewFragment = RecyclerViewFragment.newInstance(mascotasList);
        mMascotaFragment = MascotaFragment.newInstanceDetail(mascotasList);

        setUpViewPager();

        if (mtoolbar != null){
            setSupportActionBar(mtoolbar);
        }

    }

    public ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(mRecyclerViewFragment);
        fragments.add(mMascotaFragment);
        return fragments;
    }

    private void setUpViewPager(){

        mviewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments(),1));
        mTabLayout.setupWithViewPager(mviewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.mipmap.dog_house));
        mTabLayout.getTabAt(0).setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED);
        mTabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.mipmap.dog_face));
        mTabLayout.getTabAt(1).setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mMenu) {
        getMenuInflater().inflate(R.menu.menu_opciones, mMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContacto:
                saveData();
                Intent intent = new Intent(this, Contacto.class);
                startActivity(intent);
                break;
            case R.id.mAcercade:
                saveData();
                Intent intent2 = new Intent(this, Acercade.class);
                startActivity(intent2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void irMascotasFavoritas5(View v) {
        saveData();
        Intent intent = new Intent(this, MascotasFavoritas5.class);
        intent.putParcelableArrayListExtra("MascotasFavoritas", mascotasList);
        startActivityForResult(intent,1);
    }

    private void loadData(){
        SharedPreferences mSharedPreferences = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String mJson = mSharedPreferences.getString(TASK_LIST,null);
        Type mType = new TypeToken<ArrayList<Mascota>>() {}.getType();
        mascotasList = gson.fromJson(mJson,mType);

        if (mascotasList == null) {
            mascotasList = new ArrayList<Mascota>();
            mascotasList.add(new Mascota(R.drawable.beagle, R.mipmap.bone_like,"Pepe",2, R.mipmap.bone_count));
            mascotasList.add(new Mascota(R.drawable.bulldog, R.mipmap.bone_like, "Luna",5, R.mipmap.bone_count));
            mascotasList.add(new Mascota(R.drawable.bullterrier, R.mipmap.bone_like, "Tank",6, R.mipmap.bone_count));
            mascotasList.add(new Mascota(R.drawable.pug, R.mipmap.bone_like, "Luis",4, R.mipmap.bone_count));
            mascotasList.add(new Mascota(R.drawable.pequines, R.mipmap.bone_like, "Mamba",3, R.mipmap.bone_count));

        }
    }

    private void saveData(){
        SharedPreferences mSharedPreferences = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String mJson = gson.toJson(mascotasList);
        mEditor.putString(TASK_LIST,mJson);
        mEditor.apply();
    }

    @Override
    public void onmascotasListChange(ArrayList<Mascota> sendMascotasList) {
        mascotasList = sendMascotasList;
    }

    @Override
    public void onMascotaDetailListChange(ArrayList<Mascota> sendMascotasListDetail) {
        mascotasList = sendMascotasListDetail;
    }
}