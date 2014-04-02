package com.martin.jtests.serializing.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.martin.jtests.serializing.Commands;
import com.martin.jtests.serializing.Parser;

public class ParserTest {

	@Test
	public void testGenerate() {
		assertEquals("Command", Parser.getAction("Generate 1000").getCommand(), Commands.GENERATE);
		assertEquals("Data", Parser.getAction("Generate 1000").getData(), "1000");
	}
	
	@Test
	public void testWrite() {
		assertEquals("Command", Parser.getAction("WRITE hello man").getCommand(), Commands.WRITE);
		assertEquals("Data", Parser.getAction("WRITE hello man").getData(), "hello man");
	}
	
	@Test
	public void testRead() {
		assertEquals("Command", Parser.getAction("read omg, das ist צהü!").getCommand(), Commands.READ);
		assertEquals("Data", Parser.getAction("read omg, das ist צהü!").getData(), "omg, das ist צהü!");
	}
	
	@Test
	public void testUnknown() {
		assertEquals("Command", Parser.getAction("reed 456").getCommand(), Commands.UNKNOWN);
	}

}
