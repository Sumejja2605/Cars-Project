import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse("C:\\Users\\HP\\IdeaProjects\\ProjectCars\\src\\Cars.xml");

        document.getDocumentElement().normalize();
        XPath xPath = XPathFactory.newInstance().newXPath();

        getNode(document,xPath);

    }

    private static void getNode(Document document, XPath xPath) {

        XPathExpression xPathExpression;
        Object o = null;

        try {
            Scanner sc = new Scanner(System.in);
            int FirstYear;
            int SecondYear;

            System.out.println("Enter first year: ");
            FirstYear = sc.nextInt();
            System.out.println("Enter second year: ");
            SecondYear = sc.nextInt();

            xPathExpression = xPath.compile("/cars/car[production-year[. >= " + FirstYear + " and . <=" + SecondYear + "]]");
            o = xPathExpression.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        NodeList nodeList = (NodeList) o;

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node car = nodeList.item(i);
            NodeList nodeList1 = car.getChildNodes();

            for (int j = 0; j < nodeList1.getLength(); j++) {
                Node carChild = nodeList1.item(j);
                if (carChild.getNodeType() != Node.ELEMENT_NODE)
                    continue;
                System.out.println(carChild.getNodeName() + " : " + carChild.getTextContent());


            }
            System.out.println();
        }
    }
}
