package es.iessaladillo.pedrojoya.intents.ui.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

//Claves para los pokemon guardados en el ViewModel
private const val POKEMON_1 = "POKEMON_1"
private const val POKEMON_2 = "POKEMON_2"

class BattleActivityViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    //Se define el valor del atributo privado de tipo MutableLiveData para que se reciba el valor anterior
    //a la destruccion de la actividad indicando su clave (el pokemon seleccionado)
    //Si no se ha guardado un valor previo se genera uno aleatorio
    private val _firstPokemon: MutableLiveData<Pokemon> = savedStateHandle.getLiveData(POKEMON_1, Database.getRandomPokemon())
    //Se define la variable tipo LiveData de tipo Pokemon para que sea observado por otras actividades
    //Recibe el valor de la variable privada de tipo MutableLiveData
    val firstPokemon: LiveData<Pokemon> get() = _firstPokemon
    //Lo mismo con el segundo pokemon seleccionado
    private val _secondPokemon: MutableLiveData<Pokemon> = savedStateHandle.getLiveData(POKEMON_2, Database.getRandomPokemon())
    val secondPokemon: LiveData<Pokemon> get() = _secondPokemon

    //Defino el metodo de la batalla en el ViewModel de BattleActivity
    fun battle(pokemon1: Pokemon, pokemon2: Pokemon): Pokemon {
        //Metodo que decide el ganador de la batalla por el atributo de fuerza de los pokemon recibidos por parametro
        if (pokemon1.combatPower >= pokemon2.combatPower) {
            return pokemon1
        }

        return pokemon2
    }

    //Metodos que actualizan el valor de las variables privadas
    fun changePokemon1(pokemon: Pokemon) {
        _firstPokemon.value = pokemon
    }
    fun changePokemon2(pokemon: Pokemon) {
        _secondPokemon.value = pokemon
    }
}