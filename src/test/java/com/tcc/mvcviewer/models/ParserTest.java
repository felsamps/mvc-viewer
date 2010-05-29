package com.tcc.mvcviewer.models;


import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felsamps
 */
public class ParserTest {
	List<String> paths;
	TraceFileParser parser;
	Video video;
	

    public ParserTest() {
    }

	@Before
	public void before() {
		paths = new ArrayList<String> ();
		paths.add("/home/felsamps/Tcc/mvc-viewer/data/test_data.dat");
		parser = new TraceFileParser(640, 480, 1, 5, paths);
	}

	@Test
	public void parserTest() {
		video = parser.parser();
	}


}