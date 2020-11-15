package es.iessaladillo.pedrojoya.intents.ui.battle

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity

class BattleActivity : AppCompatActivity() {

    private val viewModel: BattleActivityViewModel by viewModels()
    private lateinit var binding: BattleActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BattleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        observePokemon()
    }

    private val dataSelectionCall1 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        //Metodo para esperar una respuesta de una actividad que se va a abrir
        val resultIntent = result.data
        //Si el resultado obtenido muestra que es correcto y no es nulo...
        if (result.resultCode == Activity.RESULT_OK && resultIntent != null) {
            //...se muestra el pokemon seleccionado en la actividad a la que se ha llamado
            viewModel.changePokemon1(resultIntent.getParcelableExtra("POKEMON")!!)
        }
    }

    private val dataSelectionCall2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        //Metodo para esperar una respuesta de una actividad que se va a abrir
        val resultIntent = result.data
        //Si el resultado obtenido muestra que es correcto y no es nulo...
        if (result.resultCode == Activity.RESULT_OK && resultIntent != null) {
            //... se cambia el valor guardado en el ViewModel por el que el que el intent contiene
            viewModel.changePokemon2(resultIntent.getParcelableExtra("POKEMON")!!)
        }
    }

    //Metodo para observar datos de la clase ViewModel asignada a esta clase
    private fun observePokemon() {
        //Se observa si la variable asignada al primer pokemon cambia su valor
        //El propietario del ViewModel es esta clase y usa una clase anonima que implementa interfaz
        //funcional Observer de tipo Pokemon
        //Si la variable cambia de valor se ejecuta el metodo newPokemon1()
        viewModel.firstPokemon.observe(this) { pokemon -> newPokemon1(pokemon) }
        //Se observa si la variable asignada al segundo pokemon cambia su valor
        //El propietario del ViewModel es esta clase y usa un objeto Observer de tipo Pokemon
        viewModel.secondPokemon.observe(this) { pokemon -> newPokemon2(pokemon) }
    }
    //Metodo que muestra el nombre y la imagen del primer pokemon cuando se cambia el pokemon guardado en el ViewModel
    //y que se recibe por parametro
    private fun newPokemon1(pokemon: Pokemon) {
        binding.lblPkmn1Name.text = pokemon.name
        binding.mainPkmn1Icon.setImageResource(pokemon.image)
    }
    //Metodo que muestra el nombre y la imagen del primer pokemon cuando se cambia el pokemon guardado en el ViewModel
    //y que se recibe por parametro
    private fun newPokemon2(pokemon: Pokemon) {
        binding.lblPkmn2Name.text = pokemon.name
        binding.mainPkmn2Icon.setImageResource(pokemon.image)
    }

    //Metodo que muestra una nueva actividad de la clase WinnerActivity para mostrar el ganador de la batalla
    //y que se recibe por parametro
    private fun showWinner(pokemon: Pokemon) {
        startActivity(WinnerActivity.newIntent(this, pokemon))
    }


    //Metodo que muestra una nueva actividad de la clase SelectionActivity para seleccionar el primer pokemon
    //guardado en el ViewModel
    private fun chooseFirstOpponent() {
        dataSelectionCall1.launch(SelectionActivity.newIntent(this, viewModel.firstPokemon.value!!))
    }

    //Metodo que muestra una nueva actividad de la clase SelectionActivity para seleccionar el segundo pokemon
    //guardado en el ViewModel
    private fun chooseSecondOpponent() {
        dataSelectionCall2.launch(SelectionActivity.newIntent(this, viewModel.secondPokemon.value!!))
    }

    //Metodo de configuracion de las vistas de la actividad
    private fun setupViews() {
        binding.mainBtn.setOnClickListener {
            showWinner(viewModel.battle(viewModel.firstPokemon.value!!, viewModel.secondPokemon.value!!))
        }

        binding.mainPkmn1.setOnClickListener(View.OnClickListener {
            chooseFirstOpponent()
        })

        binding.mainPkmn2.setOnClickListener(View.OnClickListener {
            chooseSecondOpponent()
        })
    }
}