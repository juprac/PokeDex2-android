package Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonListresponse {
    private int count;
    private String next;
    private String previous;
    @SerializedName("results")
    private List<Pokemon> PokemonList;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Pokemon> getPokemonList() {
        return PokemonList;
    }
}
