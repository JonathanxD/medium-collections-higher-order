package com.github.jonathanxd.medium.collectionshigherorder

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * All tests bellow are not real and trustful, they only show practical cases of higher order functions
 * in lazy evaluated sequences.
 */
class HigherOrderFunctionsTest {
    @Test
    fun `filter model age less than 10`() {
        val filter = generateCars()
                .filter { it.modelAge < 10 }
                .take(10)
                .toList()

        assertEquals(expected = 10, actual = filter.size)
        assertTrue { filter.all { it.modelAge < 10 } }
    }

    @Test
    fun `filter model age less than 10 and map car to price`() {
        val filterAndMap = generateCars()
                .filter { it.modelAge < 10 }
                .map { it.price }
                .take(10)
                .toList()

        assertEquals(expected = 10, actual = filterAndMap.size)
        assertTrue { filterAndMap.all { it >= 5000.0 && it < 50000.0 } }
    }

    @Test
    fun `filter, map car to price and reduce`() {
        val result = generateCars()
                .filter { it.modelAge < 10 }
                .map { it.price }
                .take(5)
                .reduce { acc, d -> ((acc + d) - ((acc + d) * 0.10)) }

        assertTrue { result > 0 }
    }

    @Test
    fun `apply operations with flatMapped sequences`() {
        class CarDealership(val cars: Sequence<Car>)

        val result = generateSequence { CarDealership(generateCars()) }
                .take(3)
                .flatMap { it.cars.filter { car -> car.modelAge < 10 }.take(3) }
                .map { it.price }
                .take(9)
                .reduce { acc, d -> ((acc + d) - ((acc + d) * 0.10)) }

        assertTrue { result > 0 }
    }

    @Test
    fun `group by fuel type`() {
        val grouped = generateCars().take(20).groupBy { it.fuelType }

        assertTrue { grouped.isNotEmpty() }
    }

    @Test
    fun `associated by unique id`() {
        val associated = generateCars().take(20).associateBy { it.uniqueId }

        assertTrue { associated.isNotEmpty() }
    }

    @Test
    fun `distinct by car model`() {
        val distinct = generateCars().take(100).distinctBy { it.model }.toList()

        assertTrue { distinct.distinct() == distinct }
    }

    @Test
    fun `fold into car truck`() {
        class CarTruck(val cars: List<Car> = emptyList()) {
            operator fun plus(car: Car) = CarTruck(this.cars + car)
        }

        val fold = generateCars().take(4).fold(CarTruck()) { acc, car -> acc + car }

        assertEquals(expected = 4, actual = fold.cars.size)
    }

    @Test
    fun `windowed with size 4 and partialWindows`() {
        class CarTruck(val cars: List<Car> = emptyList()) {
            operator fun plus(car: Car) = CarTruck(this.cars + car)
        }

        val windows = generateCars()
                .windowed(4, partialWindows = true)
                .map { CarTruck(cars = it) }
                .take(5)
                .toList()

        assertEquals(expected = 5, actual = windows.size)
        assertTrue { windows.all { it.cars.size == 4 } }
    }

}