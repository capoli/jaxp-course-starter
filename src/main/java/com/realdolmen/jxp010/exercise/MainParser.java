package com.realdolmen.jxp010.exercise;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by OCPAX79 on 24/09/2015.
 */
public class MainParser {
    public static void main(String[] args) {
//        runParser("movies.xml", new FindMovieTitle());
        runParser("pom.xml", new FindDependencyGAV());
    }

    private static void runParser(String fileName, DefaultHandler defaultHandler) {
        XMLReader parser;
        String file = MainParser.class.getResource(String.format("/%s", fileName)).getFile();
        try {
            SAXParserFactory sf = SAXParserFactory.newInstance();
            sf.setNamespaceAware(true);
            sf.setValidating(false);
            parser = sf.newSAXParser().getXMLReader();
            parser.setContentHandler(defaultHandler);
            parser.parse(file);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}
