package es.iessaladillo.pedrojoya.intents.ui.winner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding

class WinnerActivity : AppCompatActivity() {

    private lateinit var binding: WinnerActivityBinding
    private lateinit var winner: Pokemon

    companion object {
        //Definicion del tipo de dato que se recibe en los intent que quieran llamar a esta actividad
        const val POKEMON = "POKEMON"
        fun newIntent(context: Context, pokemon: Pokemon): Intent {
            return Intent(context, WinnerActivity::class.java)
                .putExtra(POKEMON, pokemon)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WinnerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiveData()
        setupViews()
    }

    private fun receiveData() {
        //Carga el pokemon ganador recibido por el intent de la actividad llamadora (BattleActivity)
        winner = intent.getParcelableExtra(POKEMON)!!  //AVISO DE QUE EL POKEMON RECIBIDO NO ES NULO
    }

    private fun setupViews() {
        //Se muestra el nombre y la imagen del pokemon recibido
        binding.nameWinner.text = winner.name
        binding.imageView3.setImageResource(winner.image)
    }
}