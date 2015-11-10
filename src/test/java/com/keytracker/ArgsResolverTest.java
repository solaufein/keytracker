package com.keytracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsResolverTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldReturnAllGivenArguments() throws Exception {
        String[] args = {"mail=rad@op.pl", "crypt=true", "file=true"};
        ArgsResolver argsResolver = new ArgsResolver(args);

        assertEquals(3, argsResolver.getArguments().size());
    }

    @Test
    public void shouldReturnOnlyMailArgument() throws Exception {
        String[] args = {"mail=rad@op.pl", "crypt=false", "file=false"};
        ArgsResolver argsResolver = new ArgsResolver(args);

        assertEquals(1, argsResolver.getArguments().size());
        assertEquals("rad@op.pl", argsResolver.getArguments().get("mail"));
    }

    @Test
    public void shouldReturnNothingIfMailFormatIsIncorrect() throws Exception {
        String[] args = {"mail=true", "crypt=false", "file=false"};
        ArgsResolver argsResolver = new ArgsResolver(args);

        assertEquals(0, argsResolver.getArguments().size());
    }
}