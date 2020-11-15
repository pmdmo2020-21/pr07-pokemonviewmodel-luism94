package es.iessaladillo.pedrojoya.intents.data.local

import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import kotlin.random.Random

object Database: DataSource {
    private val bulbasaur = Pokemon(1, "Bulbasaur", 1115, es.iessaladillo.pedrojoya.intents.R.drawable.bulbasur)
    private val pikachu = Pokemon(2, "Pikachu", 938, es.iessaladillo.pedrojoya.intents.R.drawable.pikachu)
    private val cubone = Pokemon(3, "Cubone", 1019, es.iessaladillo.pedrojoya.intents.R.drawable.cubone)
    private val feebas = Pokemon(4, "Feebas", 274, es.iessaladillo.pedrojoya.intents.R.drawable.feebas)
    private val gyarados = Pokemon(5, "Gyarados", 3391, es.iessaladillo.pedrojoya.intents.R.drawable.gyarados)
    private val giratina = Pokemon(6,"Giratina",3379, es.iessaladillo.pedrojoya.intents.R.drawable.giratina)
    private val pokedex = listOf(bulbasaur, pikachu, cubone, feebas, gyarados, giratina)

    override fun getRandomPokemon(): Pokemon {
        val id: Int = Random.nextInt(0, 6)
        return pokedex[id]
    }

    override fun getAllPokemons(): List<Pokemon> {
        return pokedex
    }

    override fun getPokemonById(id: Long): Pokemon? {
        var pokemon: Pokemon? = null

        for (pokemonInList in pokedex) {
            if (pokemonInList.id == id) {
                pokemon = pokemonInList
            }
        }

        return pokemon
    }
}