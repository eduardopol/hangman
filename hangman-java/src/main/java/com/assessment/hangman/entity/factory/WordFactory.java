package com.assessment.hangman.entity.factory;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class WordFactory {

    public String generateWord() {
        try {
            File xmlFile = new File("src/words/words.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            List<String> words = getWords(doc);

            return words.get(new Random().nextInt(words.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "DELL";
    }

    private List<String> getWords(Document doc) {
        NodeList nodeList = ((Element) doc.getElementsByTagName("word_list").item(0)).getElementsByTagName("word");
        List<String> words = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            words.add(nodeList.item(i).getTextContent());
        }
        return words;
    }

}
