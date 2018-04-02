package pl.lunchbuddy.server.util

import org.apache.commons.lang3.RandomStringUtils

class ShortCodeGenerator(private val count: Int) : CodeGenerator {

    private val ALPHABET: String = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override fun generate(): String {
        return RandomStringUtils.random(count, ALPHABET)
    }
}