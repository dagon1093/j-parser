package org.jparser.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class HtmlParserApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(HtmlParserApplication.class, args);

		Document doc = Jsoup.connect("http://www.ph4.ru/eng_irregular.php?al=a#google_vignette").get();
		Elements elements = doc.select("body > table > tbody > tr > td > div > table > tbody > tr");

		String regex1 = "<td class=\"silver\">(\\d+)</td>\n" +
				" <td><b>(\\w+)</b> (.+?)</td>\n" +
				" <td><b>(\\w+)</b> (.+?)</td>\n" +
				" <td><b>(\\w+)</b> (.+?)</td>\n" +
				" <td class=\"silver\">([а-яёА-ЯЁ,; ]+)</td>";

		String regex2 = "<td class=\"silver\">(\\d+)</td>\n" +
				" <td><b>(\\w+)</b>(.*?)</td>\n";
		Pattern pattern = Pattern.compile(regex1);

		System.out.println(elements.get(4).toString());


		Matcher matcher = pattern.matcher(elements.get(4).toString());
		while(matcher.find()){
				System.out.println("Group1: " + matcher.group(0));
				System.out.println("Group2: " + matcher.group(1));
				System.out.println("Group3: " + matcher.group(2));
				System.out.println("Group4: " + matcher.group(3));
				System.out.println("Group5: " + matcher.group(4));
				System.out.println("Group6: " + matcher.group(5));
				System.out.println("Group7: " + matcher.group(6));
				System.out.println("Group8: " + matcher.group(7));
				System.out.println("Group9: " + matcher.group(8));
			}

		System.out.println(matcher.groupCount());

	}

}
