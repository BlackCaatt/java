package example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private static String checkEnter;
    private static String url;
    private static int count;

    private static Document getPage() throws IOException {
      //  String url = "http://rst.ua/oldcars/?task=newresults&make%5B%5D=0&year%5B%5D=0&year%5B%5D=0&price%5B%5D=0&price%5B%5D=0&engine%5B%5D=0&engine%5B%5D=0&gear=0&fuel=0&drive=0&condition=0&from=sform&start=2";
        Scan();
        if (checkEnter.equals("")) {
            url = "http://rst.ua/oldcars/?task=newresults&make%5B%5D=0&year%5B%5D=0&year%5B%5D=0&price%5B%5D=0&price%5B%5D=0&engine%5B%5D=0&engine%5B%5D=0&gear=0&fuel=0&drive=0&condition=0&from=sform&start=1";
        }
        else {
            url = "http://rst.ua/oldcars/" + checkEnter;
        }
        System.out.println(url);

        Document page = (Document) Jsoup.connect(url).get();
        return page;
    }

    public static void Scan(){
        System.out.println("Введите модель/область");
        Scanner scn = new Scanner(System.in);
        checkEnter = scn.nextLine();
    }

    public static void main (String[] args) throws IOException {
        Document page = getPage();
        Elements info = page.select("div[class=rst-ocb-i rst-ocb-i-premium rst-uix-radius rst-ocb-i-crash], div[class=rst-ocb-i rst-ocb-i-premium rst-uix-radius], div[class=rst-ocb-i]");
        FileWriter file = new FileWriter("myJSON.json");

        for (Element element : info){

            JSONObject obj = new JSONObject();
            Element linkALL = info.select("a").first();
            String link = linkALL.attr("href");
           // Element element1 = info.select("a[href]").first();

            obj.put(count, element.text());
            count++;
            System.out.println(count);
            file.write("\n");
            System.out.println(element.text());
            String withRST = "rst.ua" + link;
            obj.put("link: " + count,withRST);
            file.write(obj.toString());
            file.flush();

           System.out.println(withRST);
        }

    }

}


