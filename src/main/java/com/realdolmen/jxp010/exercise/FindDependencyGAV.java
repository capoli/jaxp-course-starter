package com.realdolmen.jxp010.exercise;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by OCPAX79 on 24/09/2015.
 * find groupid, artifactid, version for dependencies
 */
public class FindDependencyGAV extends DefaultHandler {
    String groupId = "", artifactId = "", version = "";
    final String dependencyTag = "dependency", groupIdTag = "groupId", artifactIdTag = "artifactId", versionTag = "version";
    boolean foundDependency = false, foundGroupId = false, foundArtifactId = false, foundVersion = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals(dependencyTag)) {
            foundDependency = true;
        } else if (foundDependency && localName.equals(groupIdTag)) {
            foundGroupId = true;
        } else if (foundGroupId && localName.equals(artifactIdTag)) {
            foundArtifactId = true;
        } else if (foundArtifactId && localName.equals(versionTag)) {
            foundVersion = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (foundVersion && localName.equals(versionTag)) {
            foundDependency = false;
            foundGroupId = false;
            foundArtifactId = false;
            foundVersion = false;
            System.out.println(String.format("groupId: %-30s, artifactId: %-30s, version: %-10s",
                    groupId.trim(), artifactId.trim(), version.trim()));
            groupId = "";
            artifactId = "";
            version = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (foundGroupId && !foundArtifactId && !foundVersion) groupId += new String(ch, start, length);
        else if (foundGroupId && foundArtifactId && !foundVersion) artifactId += new String(ch, start, length);
        else if (foundGroupId && foundArtifactId && foundVersion) version += new String(ch, start, length);
    }
}
