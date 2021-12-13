package org.jparser.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
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

		String regex2 = "^(.+?)<br><b>(\\w+)</b> (.+?)$";

		Pattern pattern = Pattern.compile(regex1);
		Pattern pattern2 = Pattern.compile(regex2);


		String present;
		String presentTranscription;
		String past;
		String pastTranscription;
		String past2;
		String past2Transcription;
		String participle;
		String participleTrascription;
		String participle2;
		String participle2Trascription;
		String translate;
		boolean found;

		for (int i = 0; i < elements.size(); i++) {

			Matcher matcher = pattern.matcher(elements.get(i).toString());

			if (matcher.find()) {

				present = matcher.group(2);
				presentTranscription = matcher.group(3);
				past = matcher.group(4);
				pastTranscription = matcher.group(5);
				participle = matcher.group(6);
				participleTrascription = matcher.group(7);
				translate = matcher.group(8);

				found = matcher.group(5).contains("<br>");

			} else	{
				continue;
			}
			if (found) {
				String newMatch = pastTranscription.replaceAll("(?m)^\\s+", "");
				Matcher matcher2 = pattern2.matcher(newMatch);
				if (matcher2.find()) {
					pastTranscription = matcher2.group(1);
					past2 = matcher2.group(2);
					past2Transcription = matcher.group(3);

				}
				newMatch = participleTrascription.replaceAll("(?m)^\\s+", "");
				matcher2 = pattern2.matcher(newMatch);
				if (matcher2.find()) {
					participleTrascription = matcher2.group(1);
					participle2 = matcher2.group(2);
					participle2Trascription = matcher.group(3);
				}

			}

			System.out.println(present);
//			System.out.println(presentTranscription);
			System.out.println(past);
//			System.out.println(pastTranscription);
//			System.out.println(past2);
//			System.out.println(past2Transcription);
//			System.out.println(participle);
//			System.out.println(participleTrascription);
//			System.out.println(participle2);
//			System.out.println(participle2Trascription);
//			System.out.println(translate);

		}

	}



}
