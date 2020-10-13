package Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonByIdResponse {
    private List<Abilities> abilities;
    @SerializedName("base_experience")
    private int baseExperiense;
    @SerializedName("game_indices")
    private List<Games> games;
    private String name;
    private int id;
    private Sprite sprites;

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public int getBaseExperiense() {
        return baseExperiense;
    }

    public List<Games> getGames() {
        return games;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Sprite getSprites() {
        return sprites;
    }
}
