package Network;

import Models.PokemonByIdResponse;
import Models.PokemonListresponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonLoader implements PokemonApi {
    PokemonApi pokemonApi;
    final String URL_BASE = "https://pokeapi.co/api/v2/";

    public PokemonLoader() {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL_BASE)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        pokemonApi = retrofit.create(PokemonApi.class);
    }

    @Override
    public Call<PokemonListresponse> getPokemonList() {
        return pokemonApi.getPokemonList();
    }

    @Override
    public Call<PokemonByIdResponse> getPokemonById(String id) {
        return pokemonApi.getPokemonById(id);
    }
}
