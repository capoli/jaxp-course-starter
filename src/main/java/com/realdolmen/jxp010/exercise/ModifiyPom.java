package com.realdolmen.jxp010.exercise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Created by OCPAX79 on 24/09/2015.
 */
public class ModifiyPom {
    public static Document addDependency(Document document) {
        NodeList dependencies = document.getElementsByTagName("dependencies");
        if(dependencies.getLength() == 1) {
            dependencies.item(0).appendChild(createDependency(document));
        }
        return document;
    }

    private static Element createDependency(Document document) {
        Element dependency = document.createElement("dependency");
        dependency.appendChild(createSimpleElement(document, "groupId", "lol"));
        dependency.appendChild(createSimpleElement(document, "artifactId", "lol"));
        dependency.appendChild(createSimpleElement(document, "version", "lol"));
        return dependency;
    }

    private static Element createSimpleElement(Document document, String groupId, String text) {
        Element element = document.createElement(groupId);
        element.setTextContent(text);
        return element;
    }


}
