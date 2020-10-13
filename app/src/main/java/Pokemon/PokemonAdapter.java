package Pokemon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.pokedex2.PokemonDetailActivity;
import com.helloworld.pokedex2.R;

import java.util.List;

import Models.Pokemon;
import utils.Constant;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    List<Pokemon> PokemonList;
    Context ctx;

    public PokemonAdapter(List<Pokemon> pokemonList, Context ctx) {
        PokemonList = pokemonList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PokemonViewHolder holder, int i) {
        holder.tvPokemonName.setText(PokemonList.get(i).getName());

        holder.tvPokemonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pokemonId = PokemonList.get(holder.getAdapterPosition()).getName();

                Intent intent = new Intent(ctx, PokemonDetailActivity.class);
                intent.putExtra(Constant.EXTRA_POKEMON_ID, pokemonId);
                ctx.startActivity(intent);

            }
        });

        holder.btPokemonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PokemonList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return PokemonList.size();
    }
}
