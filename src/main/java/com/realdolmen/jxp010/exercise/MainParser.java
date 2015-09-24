package com.realdolmen.jxp010.exercise;

import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by OCPAX79 on 24/09/2015.
 */
public class MainParser {
    private static String FILENAME = "pom.xml";

    public static void main(String[] args) {
        String fileName = MainParser.class.getResource(String.format("/%s", FILENAME)).getFile();
//        runSAXParser(fileName, new FindMovieTitle());
//        runSAXParser(fileName, new FindDependencyGAV());
        runDomParser(fileName);
    }

    private static void runDomParser(String file) {
//        String fileLocal = MainParser.class.getResource(String.format("/%s", fileName)).getFile();
        try {
            File fileObj = new File("./src/main/resources/test.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document document = factory.newDocumentBuilder().parse(file);
            document = ModifiyPom.addDependency(document);

            DOMBuilder builder = new DOMBuilder();
            org.jdom2.Document jdoc = builder.build(document);

            boolean exists = fileObj.exists();

            new XMLOutputter(Format.getPrettyFormat()).output(jdoc, new FileWriter(fileObj));
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.transform(new DOMSource(document), new StreamResult(new FileWriter()));
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        /*if(file.exists()) {
            XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.setFormat(Format.getPrettyFormat());
            xmlOutputter.output(document, new FileWriter(file));
        }*/
    }

    private static void runSAXParser(String file, DefaultHandler defaultHandler) {
        XMLReader parser;
//        String file = MainParser.class.getResource(String.format("/%s", fileName)).getFile();
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
