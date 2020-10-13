package Network;

import Models.PokemonByIdResponse;
import Models.PokemonListresponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {
    @GET("pokemon")
    Call<PokemonListresponse> getPokemonList();

    @GET("pokemon/{id}")
    Call<PokemonByIdResponse> getPokemonById(@Path("id") String id);

}
