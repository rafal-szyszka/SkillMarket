package it.szyszka.skillmarket.api;

import org.junit.Test;

import it.szyszka.skillmarket.utils.forms.StringValidator;

import static org.junit.Assert.assertEquals;

/**
 * Created by rafal on 30.09.17.
 */

public class StringValidatorTest {

    @Test
    public void shouldReturnTrueOnValidNotEmpty() {
        assertEquals(true, StringValidator.validNotEmpty("Not Empty"));
    }

    @Test
    public void shouldReturnFalseOnValidNotEmpty() {
        assertEquals(false, StringValidator.validNotEmpty(""));
    }

    @Test
    public void shouldReturnTrueOnValidEmail() {
        assertEquals(true, StringValidator.validEmail("rafal.sz.49@gmail.pwr.edu.pl.com"));
    }

    @Test
    public void shouldReturnFalseOnValidEmail() {
        assertEquals(false, StringValidator.validEmail("rafa@l.sz.49@gmail.com"));
    }

    @Test
    public void shouldReturnTrueOnValidNoWhiteSpaces() {
        assertEquals(true, StringValidator.validNoWhiteSpaces("rafal.sz.49@gmail.com"));
    }

    @Test
    public void shouldReturnFalseOnValidNoWhiteSpaces() {
        assertEquals(false, StringValidator.validNoWhiteSpaces("rafa l.sz.49@gmail.com"));
    }

    @Test
    public void shouldReturnTrueOnValidFullName() {
        assertEquals(true, StringValidator.validFullName("Rafal Szyszka"));
        assertEquals(true, StringValidator.validFullName("Rafal Patryk Szyszka"));
    }

    @Test
    public void shouldReturnFalseOnValidFullName() {
        assertEquals(false, StringValidator.validFullName("Justyna"));
    }

}
