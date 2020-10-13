package Pokemon;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helloworld.pokedex2.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    TextView tvPokemonName;
    Button btPokemonDelete;
    LinearLayout llPokemonContainer;

    public PokemonViewHolder(@NonNull View v) {
        super(v);
        tvPokemonName = v.findViewById(R.id.tvPokemonName);
        llPokemonContainer = v.findViewById(R.id.llPokemonContainer);
        btPokemonDelete = v.findViewById(R.id.btPokemonDelete);

    }
}
