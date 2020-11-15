package es.iessaladillo.pedrojoya.intents.ui.selection

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding

class SelectionActivity : AppCompatActivity() {

    private lateinit var binding: SelectionActivityBinding
    //El pokemon recibido en el intent se guarda para mostrarlo seleccionado al iniciar la actividad
    private lateinit var pokemonSelection: Pokemon

    companion object {
        //Defino el objeto compañero de la clase para definir un metodo que especifique el tipo de dato que se va
        //a enviar a la otra actividad
        //Constante String para definir el tipo de dato que envia el intent
        const val POKEMON = "POKEMON"
        //Metodo que define los intent que quieren abrir una nueva actividad de esta clase para seleccionar
        //uno de los dos pokemon y lo devuelve
        fun newIntent(context: Context, pokemon: Pokemon): Intent {
            return Intent(context, SelectionActivity::class.java)
                .putExtra(POKEMON, pokemon)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        receiveData()
        selectPokemon()
    }

    //Se guarda el pokemon recibido en el intent que ha llamado a la actividad actual de forma parcelable
    private fun receiveData() {
        pokemonSelection = intent.getParcelableExtra(POKEMON)!!
    }

    private fun setupViews() {
        showPokemon()
        setImageClickListeners()
        setRadioButtonClickListeners()
    }

    //Metodo que muestra los pokemon definidos en la base de datos
    private fun showPokemon() {
        //Defino un iterador de la lista que se recibe de la base de datos con todos los pokemon en ella
        val iterator = Database.getAllPokemons().listIterator()
        //Defino el pokemon al que el iterador apunta en cada momento
        //Se marca ahora al primero
        var pokemon = iterator.next();
        //A cada pokemon de la lista se muestra su imagen y su RadioButton con su nombre
        binding.iconPkmn1.setImageResource(pokemon.image)
        binding.optionPkmn1.text = pokemon.name

        pokemon = iterator.next();
        binding.iconPkmn2.setImageResource(pokemon.image)
        binding.optionPkmn2.text = pokemon.name

        pokemon = iterator.next();
        binding.iconPkmn3.setImageResource(pokemon.image)
        binding.optionPkmn3.text = pokemon.name

        pokemon = iterator.next();
        binding.iconPkmn4.setImageResource(pokemon.image)
        binding.optionPkmn4.text = pokemon.name

        pokemon = iterator.next();
        binding.iconPkmn5.setImageResource(pokemon.image)
        binding.optionPkmn5.text = pokemon.name

        pokemon = iterator.next();
        binding.iconPkmn6.setImageResource(pokemon.image)
        binding.optionPkmn6.text = pokemon.name
    }

    private fun selectPokemon() {
        //Quito las opciones ya seleccionadas antes
        uncheckOptions()
        //Determino el pokemon a seleccionar si coincide su nombre con el de la opcion seleccionada
        if (pokemonSelection.name == binding.optionPkmn1.text) {
            binding.optionPkmn1.isChecked = true
        } else if (pokemonSelection.name == binding.optionPkmn2.text) {
            binding.optionPkmn2.isChecked = true
        } else if (pokemonSelection.name == binding.optionPkmn3.text) {
            binding.optionPkmn3.isChecked = true
        } else if (pokemonSelection.name == binding.optionPkmn4.text) {
            binding.optionPkmn4.isChecked = true
        } else if (pokemonSelection.name == binding.optionPkmn5.text) {
            binding.optionPkmn5.isChecked = true
        } else if (pokemonSelection.name == binding.optionPkmn6.text) {
            binding.optionPkmn6.isChecked = true
        }
    }

    private fun setImageClickListeners() {
        //Metodo para generar y configurar los objetos OnClickListener para las imagenes
        //Cada uno contiene un metodo para marcar la opcion de la imagen relacionada cuando se pulsa esta
        binding.iconPkmn1.setOnClickListener(View.OnClickListener { v -> choosePokemon1() })
        binding.iconPkmn2.setOnClickListener(View.OnClickListener { v -> choosePokemon2() })
        binding.iconPkmn3.setOnClickListener(View.OnClickListener { v -> choosePokemon3() })
        binding.iconPkmn4.setOnClickListener(View.OnClickListener { v -> choosePokemon4() })
        binding.iconPkmn5.setOnClickListener(View.OnClickListener { v -> choosePokemon5() })
        binding.iconPkmn6.setOnClickListener(View.OnClickListener { v -> choosePokemon6() })
    }
    private fun setRadioButtonClickListeners() {
        //Metodo para generar y configurar los objetos OnClickListener para los RadioButton
        //Cada uno contiene un metodo para marcar la opcion relacionada cuando se pulsa esta
        binding.optionPkmn1.setOnClickListener(View.OnClickListener { v -> choosePokemon1() })
        binding.optionPkmn2.setOnClickListener(View.OnClickListener { v -> choosePokemon2() })
        binding.optionPkmn3.setOnClickListener(View.OnClickListener { v -> choosePokemon3() })
        binding.optionPkmn4.setOnClickListener(View.OnClickListener { v -> choosePokemon4() })
        binding.optionPkmn5.setOnClickListener(View.OnClickListener { v -> choosePokemon5() })
        binding.optionPkmn6.setOnClickListener(View.OnClickListener { v -> choosePokemon6() })
    }

    //Metodo que se encarga de guardar el primer pokemon de la base de datos cuando se pulsa su imagen o su RadioButton
    private fun choosePokemon1() {
        //Busco en la base de datos el pokemon seleccionado por su nombre
        for (pokemonInList in Database.getAllPokemons()) {
            if (pokemonInList.name == binding.optionPkmn1.text) {
                //Se guarda el pokemon que coincide para enviarlo luego de forma parcelable a la actividad llamadora
                pokemonSelection = pokemonInList
            }
        }
        //Para asegurar limpio las opciones ya marcadas y marco la del primer pokemon de la pantalla
        uncheckOptions()
        binding.optionPkmn1.isChecked = true
    }
    //Lo mismo para el resto de los pokemon mostrados
    private fun choosePokemon2() {
        for (pokemonInList in Database.getAllPokemons()) {
            if (pokemonInList.name == binding.optionPkmn2.text) {
                pokemonSelection = pokemonInList
            }
        }

        uncheckOptions()
        binding.optionPkmn2.isChecked = true
    }
    private fun choosePokemon3() {
        for (pokemonInList in Database.getAllPokemons()) {
            if (pokemonInList.name == binding.optionPkmn3.text) {
                pokemonSelection = pokemonInList
            }
        }

        uncheckOptions()
        binding.optionPkmn3.isChecked = true
    }
    private fun choosePokemon4() {
        for (pokemonInList in Database.getAllPokemons()) {
            if (pokemonInList.name == binding.optionPkmn4.text) {
                pokemonSelection = pokemonInList
            }
        }

        uncheckOptions()
        binding.optionPkmn4.isChecked = true
    }
    private fun choosePokemon5() {
        for (pokemonInList in Database.getAllPokemons()) {
            if (pokemonInList.name == binding.optionPkmn5.text) {
                pokemonSelection = pokemonInList
            }
        }

        uncheckOptions()
        binding.optionPkmn5.isChecked = true
    }
    private fun choosePokemon6() {
        for (pokemonInList in Database.getAllPokemons()) {
            if (pokemonInList.name == binding.optionPkmn6.text) {
                pokemonSelection = pokemonInList
            }
        }

        uncheckOptions()
        binding.optionPkmn6.isChecked = true
    }

    //Metodo que limpia todos los RadioButtons marcados
    private fun uncheckOptions() {
        binding.optionPkmn1.isChecked = false
        binding.optionPkmn2.isChecked = false
        binding.optionPkmn3.isChecked = false
        binding.optionPkmn4.isChecked = false
        binding.optionPkmn5.isChecked = false
        binding.optionPkmn6.isChecked = false
    }
    //Metodo que se ejecuta cuando el usuario pulsa el boton Atras de su dispositivo
    override fun onBackPressed() {
        //Genera un intent de respuesta indicando que el dato enviado es un objeto Pokemon
        val result = Intent().putExtra(POKEMON, pokemonSelection)
        //Se marca el resultado del intent como bueno y se finaliza y se destruye la actividad actual
        setResult(Activity.RESULT_OK, result)
        finish()
        super.onBackPressed()
    }
}