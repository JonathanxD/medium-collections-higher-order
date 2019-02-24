package com.github.jonathanxd.medium.collectionshigherorder

import java.time.Year
import java.util.*

/**
 * Enumeração com os tipos de combustiveis
 */
enum class FuelType {
    ETHANOL,
    GASOLINE,
    FLEX
}

/**
 * Uma classe da dados com modelo, preco, tipo de combustivel, ano do modelo e um identificador unico.
 */
data class Car(val model: String,
               val price: Double,
               val fuelType: FuelType,
               val modelYear: Year,
               val uniqueId: UUID = UUID.randomUUID())

/**
 * Uma propriedade de extensao que calcula a idade do modelo do carro.
 */
val Car.modelAge: Int
    get() = Year.now().value - this.modelYear.value