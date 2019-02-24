package com.github.jonathanxd.medium.collectionshigherorder

import java.time.Year
import kotlin.random.Random

/**
 * Gera um nome de modelo aleatorio.
 */
fun randomFakeModel(): String =
        "C${Random.nextInt(100000) + 1}"

/**
 * Gera um preco aleatorio entre 5000.0 e 50000.0 (exclusivo)
 */
fun randomPrice(): Double =
        Random.nextDouble(5000.0, 50000.0)

/**
 * Gera um tipo de combustivel aleatorio.
 */
fun randomFuelType(): FuelType =
        FuelType.values().random(Random)

/**
 * Gera um ano de modelo aleatorio
 */
fun randomYear(): Year =
        Year.of(1970).rangeTo(Year.now()).random()

/**
 * Gera um ano aleatorio a partir de um intervalo de anos.
 */
fun ClosedRange<Year>.random(): Year =
        this.run {
            val startYear = start.value
            val endYear = endInclusive.value
            Random.nextInt(startYear, endYear)
        }.run(Year::of)


/**
 * Gera uma sequencia de carros de tamanho indefinido, sendo populada sob demanda.
 */
fun generateCars(): Sequence<Car> =
        generateSequence {
            Car(
                    model = randomFakeModel(),
                    price = randomPrice(),
                    fuelType = randomFuelType(),
                    modelYear = randomYear()
            )
        }