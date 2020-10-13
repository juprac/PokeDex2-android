package com.helloworld.pokedex2;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Game.GameAdapter;
import Models.Abilities;
import Models.Ability;
import Models.PokemonByIdResponse;
import Network.PokeCallBack;
import Network.PokemonLoader;
import Pokemon.PokemonAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Constant;

public class PokemonDetailActivity extends baseActivity {
    TextView tvPokeTitle, tvPokeXp, tvPokeAbilities;
    ImageView ivPokeSprite;
    RecyclerView rvPokeGames;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        tvPokeTitle = findViewById(R.id.tvPokeTitle);
        tvPokeXp = findViewById(R.id.tvPokeXp);
        tvPokeAbilities = findViewById(R.id.tvPokeAbilities);
        ivPokeSprite = findViewById(R.id.ivPokeSprite);
        rvPokeGames = findViewById(R.id.rvPokeGames);

        String pokemonId = getIntent().getStringExtra(Constant.EXTRA_POKEMON_ID);
        Call<PokemonByIdResponse> call = loader.getPokemonById(pokemonId);

        call.enqueue(new PokeCallBack<PokemonByIdResponse>(PokemonDetailActivity.this, true) {
            @Override
            public void onResponse(Call<PokemonByIdResponse> call, Response<PokemonByIdResponse> response) {
                super.onResponse(call, response);

                if(response.isSuccessful()){
                    tvPokeTitle.setText(response.body().getId() + " - " + response.body().getName());
                    tvPokeXp.setText("XP Base: " + response.body().getBaseExperiense());


                    Glide.with(PokemonDetailActivity.this).load(response.body().getSprites().getImage()).into(ivPokeSprite);

                    List<Ability> abilityList = new ArrayList<>();

                    StringBuilder sb = new StringBuilder();


                    for(Abilities abilities : response.body().getAbilities()){
                        abilityList.add(abilities.getAbility());
                    }
                    for(Ability ability : abilityList){
                        sb.append(ability.getName() + "\n");
                    }
                    tvPokeAbilities.setText(sb.toString());

                    GameAdapter adapter  = new GameAdapter(response.body().getGames());
                    rvPokeGames.setAdapter(adapter);
                    rvPokeGames.setHasFixedSize(true);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(PokemonDetailActivity.this);
                    rvPokeGames.setLayoutManager(manager);

                }else {
                    showDialogError();

                }
            }

            @Override
            public void onFailure(Call<PokemonByIdResponse> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }
}
