package bgs.formalspecificationide.Helpers;

import javafx.util.Pair;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParser {
    public static void parseXml(File xmlFile) {
        // Instantiate the Factory
        var dbf = DocumentBuilderFactory.newInstance();

        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            var db = dbf.newDocumentBuilder();
            var doc = db.parse(xmlFile);

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

//            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
//            System.out.println("------");

            findUseCases(doc);


            // get <staff>
//            var list = doc.getElementsByTagName("staff");
//
//            for (int temp = 0; temp < list.getLength(); temp++) {
//                var node = list.item(temp);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    var element = (Element) node;
//
//                    // get staff's attribute
//                    var id = element.getAttribute("id");
//
//                    // get text
//                    var firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
//                    var lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
//                    var nickname = element.getElementsByTagName("nickname").item(0).getTextContent();
//
//                    var salaryNodeList = element.getElementsByTagName("salary");
//                    var salary = salaryNodeList.item(0).getTextContent();
//
//                    // get salary's attribute
//                    var currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();
//
//                    System.out.println("Current Element :" + node.getNodeName());
//                    System.out.println("Staff Id : " + id);
//                    System.out.println("First Name : " + firstname);
//                    System.out.println("Last Name : " + lastname);
//                    System.out.println("Nick Name : " + nickname);
//                    System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);
//                }
//            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void findUseCases(Document doc) {
        var root = doc.getDocumentElement();
        var rootTag = root.getTagName(); // root.tag (xmi:XMI)
        var rootAttrib = getAttributesFromNamedNodeMap(root.getAttributes()); // root.attrib (xmi:version) oraz root.nsmap (xmlns:xsi, xmlns:uml, xmlns:xmi)
        var namespaces = rootAttrib; // TODO możliwe że usunąć (xmi:version)
        var ucMatches = gatherRulesUseCases(namespaces);
        var extMatches = gatherRulesExtends(namespaces);
        var incMatches = gatherRulesIncludes(namespaces);

        var ucXmlElems = gatherUcXmlElems(root);
        var useCases = getUseCasesFromUcXmlElems(ucXmlElems, namespaces);
        var useCasesList = new ArrayList<>(useCases.values());

        var extXmlElems = gatherExtXmlElems(root);
        var extend = getExtendFromExtXmlElems(extXmlElems, namespaces, useCases);

    }

    private static Map<String, Pair<String, String>> getExtendFromExtXmlElems(List<Element> extXmlElems, Map<String, String> namespaces, Map<String, String> useCases) {
        var extend = new HashMap<String, Pair<String, String>>();
        var cnt = 1;
        for (var elem: extXmlElems){
            var elementAttributes = getAttributesFromNamedNodeMap(elem.getAttributes());
            var key = "Ext" + cnt;
            if (elementAttributes.containsKey("extension")){
                var from = useCases.get(elementAttributes.get("extension"));
                var to = useCases.get(elementAttributes.get("extendedCase"));
                extend.put(key, new Pair<>(from, to));
            } else if (elementAttributes.containsKey("extendedCase")){
                var parentElementAttributes = getAttributesFromNamedNodeMap(elem.getParentNode().getAttributes());
                var from = parentElementAttributes.get("id");
                var to = useCases.get(elementAttributes.get("extendedCase"));
                extend.put(key, new Pair<>(from, to));
            } else if (elementAttributes.containsKey("From")){
                var from = useCases.get(elementAttributes.get("To"));
                var to = useCases.get(elementAttributes.get("From"));
                extend.put(key, new Pair<>(from, to));
            }
            cnt++;
        }
        return extend;
    }

    private static List<Element> gatherExtXmlElems(Element root) {
        var rootTag = root.getTagName(); // root.tag (xmi:XMI)
        var extXmlElems = new ArrayList<Element>();
        if (rootTag.equals("xmi:XMI")){
            var childOfRootNodes = root.getChildNodes();
            for (int i = 0; i < childOfRootNodes.getLength(); i++) {
                if (childOfRootNodes.item(i).getNodeType() == Node.ELEMENT_NODE && childOfRootNodes.item(i).getNodeName().equals("uml:Model")){
                    var modelNode = childOfRootNodes.item(i);
                    var childOfModelNodes = modelNode.getChildNodes();
                    for (int j = 0; j < childOfModelNodes.getLength(); j++) {
                        if (childOfModelNodes.item(j).getNodeType() == Node.ELEMENT_NODE && childOfModelNodes.item(j).getNodeName().equals("packagedElement")){
                            var packagedElementNode = childOfModelNodes.item(j);
                            var childOfPackagedElementNode = packagedElementNode.getChildNodes();
                            for (int k = 0; k < childOfPackagedElementNode.getLength(); k++) {
                                if (childOfPackagedElementNode.item(k).getNodeType() == Node.ELEMENT_NODE && childOfPackagedElementNode.item(k).getNodeName().equals("extend")){
                                    var extendNode = (Element) childOfPackagedElementNode.item(k);
                                    extXmlElems.add(extendNode);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Inny root tag name");
        }
        return extXmlElems;
    }

    private static Map<String, String> getUseCasesFromUcXmlElems(List<Element> ucXmlElems, Map<String, String> namespaces) {
        var useCases = new HashMap<String,String>();
        for (var elem: ucXmlElems){
            var elementAttributes = getAttributesFromNamedNodeMap(elem.getAttributes());
            var id = elementAttributes.get("id");
            if (elementAttributes.containsKey("Name")){
                useCases.put(id, elementAttributes.get("Name"));
            } else if (elementAttributes.containsKey("name")){
                useCases.put(id, elementAttributes.get("name"));
            } else if (elem.getTagName().equals("Foundation.Core.ModelElement.name")){
                useCases.put(id, elem.getTextContent());
            }
        }
        return useCases;
    }

    private static List<Element> gatherUcXmlElems(Element root) {
        var rootTag = root.getTagName(); // root.tag (xmi:XMI)
        var ucXmlElems = new ArrayList<Element>();
        if (rootTag.equals("xmi:XMI")){
            var childOfRootNodes = root.getChildNodes();
            for (int i = 0; i < childOfRootNodes.getLength(); i++) {
                if (childOfRootNodes.item(i).getNodeType() == Node.ELEMENT_NODE && childOfRootNodes.item(i).getNodeName().equals("uml:Model")){
                    var modelNode = childOfRootNodes.item(i);
                    var childOfModelNodes = modelNode.getChildNodes();
                    for (int j = 0; j < childOfModelNodes.getLength(); j++) {
                        if (childOfModelNodes.item(j).getNodeType() == Node.ELEMENT_NODE && childOfModelNodes.item(j).getNodeName().equals("packagedElement")){
                            var packagedElementNode = (Element) childOfModelNodes.item(j);
                            var packagedElementNodeAttributes = getAttributesFromNamedNodeMap(packagedElementNode.getAttributes());
                            var type = packagedElementNodeAttributes.get("type");
                            if (type.equals("uml:UseCase")){
                                ucXmlElems.add(packagedElementNode);
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Inny root tag name");
        }
        return ucXmlElems;
    }

    private static Map<String, String> getAttributesFromNamedNodeMap(NamedNodeMap attributesRaw){
        var attributesMap = new HashMap<String, String>();
        for (int i = 0; i < attributesRaw.getLength(); i++) {
            var node = attributesRaw.item(i);
            var nodeName = node.getNodeName();
            if (nodeName.contains(":")){
                nodeName = nodeName.split(":")[1]; // example: "xmi:version" --> "version"
            }
            attributesMap.put(nodeName, node.getTextContent());
        }
        return attributesMap;
    }

    private static List<String> gatherRulesUseCases(Map<String, String> namespaces){
        var ucMatches = new ArrayList<String>();
        ucMatches.add(".//UseCase[@Abstract='false']");    // visual paradigm 'Xml_structure': 'simple'
        ucMatches.add(".//Model[@modelType='UseCase']");   // visual paradigm 'Xml_structure': 'traditional'
        ucMatches.add(".//UMLUseCase");                    // Sinvas
        ucMatches.add(".//Behavioral_Elements.Use_Cases.UseCase/Foundation.Core.ModelElement.name");  // EnterpriseArchitect XMI 1.0 UML 1.3

        if (namespaces.containsKey("xmi")){
            ucMatches.add(".//packagedElement[@xmi:type='uml:UseCase']");  // Papyrus {'xmi': 'http://www.omg.org/spec/XMI/20131001'}
            // EnterpriseArchitect xmi 2.1 >= 2.5.1, uml 2.1 >= 2.5.1 {'xmi': 'http://schema.omg.org/spec/XMI/2.1'}
        }
        if (namespaces.containsKey("xsi")){
            ucMatches.add(".//packagedElement[@xsi:type='uml:UseCase']");  // GenMyModel {'xsi': 'http://www.w3.org/2001/XMLSchema-instance'}

        }
        if (namespaces.containsKey("UML")){
            ucMatches.add(".//UML:UseCase");   // EnterpriseArchitect XMI 1.1 UML 1.3 {'UML': 'omg.org/UML1.3'},
            // EnterpriseArchitect XMI 1.2 UML 1.4 {'UML': 'org.omg.xmi.namespace.UML'}
        }
        return ucMatches;
    }

    private static List<String> gatherRulesExtends(Map<String, String> namespaces){
        var extMatches = new ArrayList<String>();
        extMatches.add(".//Extend[@BacklogActivityId='0']");

        if (namespaces.containsKey("xmi")){
            extMatches.add(".//extend[@xmi:type='uml:Extend']");
        }
        if (namespaces.containsKey("xsi")){
            extMatches.add(".//extend");
        }
        return extMatches;
    }

    private static List<String> gatherRulesIncludes(Map<String, String> namespaces){
        var incMatches = new ArrayList<String>();
        incMatches.add(".//Include[@BacklogActivityId='0']");

        if (namespaces.containsKey("xmi")){
            incMatches.add(".//include[@xmi:type='uml:Include']");
        }
        if (namespaces.containsKey("xsi")){
            incMatches.add(".//include");
        }
        return incMatches;
    }


}
