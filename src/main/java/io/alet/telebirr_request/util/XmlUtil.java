package io.alet.telebirr_request.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class XmlUtil {


    public static Map<String, String> extract(String xml, String data) throws ParserConfigurationException, IOException, SAXException {
        Map<String, String> values = new HashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        // Get Document
        Document document = builder.parse(new InputSource(new StringReader(xml)));

        // Normalize the xml structure
        document.getDocumentElement().normalize();

        // Get all the element by the tag name

        NodeList laptopList = document.getElementsByTagName(data);
        for (int i = 0; i < laptopList.getLength(); i++) {
            Node root = laptopList.item(i);
            if (root.getNodeType() == Node.ELEMENT_NODE) {

                Element laptopElement = (Element) root;

                NodeList laptopDetails = root.getChildNodes();
                for (int j = 0; j < laptopDetails.getLength(); j++) {
                    Node detail = laptopDetails.item(j);
                    if (detail.getNodeType() == Node.ELEMENT_NODE) {
                        if (detail.getChildNodes().getLength() > 1) {
                            for (int m = 0; m < detail.getChildNodes().getLength(); m++) {
                                Element detailElement = (Element) detail.getChildNodes().item(m);
                                values.put(detailElement.getTagName().toLowerCase(), detailElement.getTextContent());
                            }
                        } else {
                            Element detailElement = (Element) detail;
                            values.put(detailElement.getTagName().toLowerCase(), detailElement.getTextContent());
                        }
                    }
                }
            }
        }

        for (var entry : values.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        return values;

    }

}
