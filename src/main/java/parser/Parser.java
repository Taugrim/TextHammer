package parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import combinator.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class Parser {
    public static void main(String[] args) throws IOException {
//        String fileName = "src/main/resources/ex.xml";
        String fileName = "src/main/resources/comn_sdict_axm05_mueller24/mueller24/dict.xml";
//        String fileName = "src/main/resources/comn_sdict_axm05_mueller24/mueller24/dict.xml";
        Path filePath = Paths.get(fileName);

        String content = Files.readString(filePath);


//        System.out.println(lines(fileName).limit(10).collect(Collectors.joining(" ")));
//        System.out.println(content);
//        Document document=XML.createDocument();
//        Node root=XML.getNodesByXpath(document,".").get(0);
        Document document = XML.getDocument(content);
        Node node = XML.getNodeByXpath(document, "//ar");
        List<Node> nodes = XML.getNodesByXpath(document, "//ar");
//        System.out.println(XML.getNodesByXpath(XML.getDocument(content),"//ar").get(0));
        System.out.println(nodes.parallelStream().filter(q->q.getFirstChild().getTextContent().equalsIgnoreCase("end")).map(q->q.getLastChild().getTextContent()).findFirst().orElse("nan"));
//        System.out.println(node.getTextContent());
        System.out.println(node.getFirstChild().getTextContent());
        System.out.println(node.getLastChild().getTextContent());



    }

    List<String> readAll() throws IOException {
        var fileName = "src/resources/thermopylae.txt";

        return Files.readAllLines(Paths.get(fileName),
                StandardCharsets.UTF_8);
    }

   static Stream<String> lines(String path) throws IOException {
        String fileName = "src/resources/thermopylae.txt";

        return Files.lines(Paths.get(path));
    }

}
