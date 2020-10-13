package com.helloworld.pokedex2;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Models.Pokemon;
import Models.PokemonListresponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Constant;
import Pokemon.PokemonAdapter;
import Network.PokeCallBack;
import utils.DialogManager;

public class PokedexActivity extends baseActivity {
    List<Pokemon> pokemonList;
    PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        final EditText etPokeAdd = findViewById(R.id.etPokeAdd);
        Button btPokemonAdd = findViewById(R.id.btPokemonAdd);

        btPokemonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poke = etPokeAdd.getText().toString();

                if (poke.isEmpty()){
                    return;
                }
                Pokemon pokemon = new Pokemon(poke,"");
                pokemonList.add(0, pokemon);
                adapter.notifyDataSetChanged();
            }
        });



         final RecyclerView rvPokemonList = findViewById(R.id.rvPokemonList);

        Call<PokemonListresponse> call = loader.getPokemonList();
        call.enqueue(new PokeCallBack<PokemonListresponse>(PokedexActivity.this, true) {
            @Override
            public void onResponse(Call<PokemonListresponse> call, Response<PokemonListresponse> response) {
                super.onResponse(call, response);

                if(response.isSuccessful()){
                    pokemonList = response.body().getPokemonList();

                    adapter  = new PokemonAdapter(pokemonList, PokedexActivity.this);
                    rvPokemonList.setAdapter(adapter);
                    rvPokemonList.setHasFixedSize(true);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(PokedexActivity.this);
                    rvPokemonList.setLayoutManager(manager);
                }else {
                    showDialogError();
                }
            }

            @Override
            public void onFailure(Call<PokemonListresponse> call, Throwable t) {
                super.onFailure(call, t);
                showDialogError();
            }
        });
    }

}