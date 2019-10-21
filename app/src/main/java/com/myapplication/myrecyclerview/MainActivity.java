package com.myapplication.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.myapplication.myrecyclerview.adapter.CardViewHeroAdapter;
import com.myapplication.myrecyclerview.adapter.GridHeroAdapter;
import com.myapplication.myrecyclerview.adapter.ListHeroAdapter;
import com.myapplication.myrecyclerview.model.Hero;
import com.myapplication.myrecyclerview.model.HeroesData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHeroes;
    private ArrayList<Hero> list;
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        list = new ArrayList<>();

        if (savedInstanceState == null) {
            setActionBarTitle("Mode List Yunus Mu'tashimbillah Almuhajiri");
            list.addAll(HeroesData.getListData());
            showRecyclerList();
            mode = R.id.action_list;

        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<? extends Hero> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTitle(stateTitle);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    private void showRecyclerList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        String title = null;
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List Yunus Mu'tashimbillah Almuhajiri";
                showRecyclerList();
                break;

            case R.id.action_grid:
                title = "Mode List Yunus Mu'tashimbillah Almuhajiri";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "Mode List Yunus Mu'tashimbillah Almuhajiri";
                showRecycleCardView();
                break;
        }
        mode = selectedMode;
        setActionBarTitle(title);
    }

    private void  showRecyclerGrid() {
        rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHeroes.setAdapter(gridHeroAdapter);

        gridHeroAdapter.setOnItemClickCallback(new GridHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }


    private void showRecycleCardView() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter cardViewHeroAdapter = new CardViewHeroAdapter(list);
        rvHeroes.setAdapter((cardViewHeroAdapter));
    }

    private void showSelectedHero(Hero hero) {
        Toast.makeText(this, "Kamu memilih " + hero.getName(), Toast.LENGTH_SHORT).show();
    }
}
