package com.realdolmen.jxp010.exercise;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by OCPAX79 on 24/09/2015.
 */
public class FindMovieTitle extends DefaultHandler {
    String title = "";
    final String movieTag = "movie", titleTag = "title";
    boolean foundMovie = false, foundTitle = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(localName.equals(movieTag)) foundMovie = true;
        else if(foundMovie && localName.equals(titleTag)) foundTitle = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(foundTitle && localName.equals(titleTag)) {
            foundTitle = false;
            foundMovie = false;
            System.out.println(String.format("title: %s", title.trim()));
            title = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(foundTitle) title += new String(ch, start, length);
    }
}
