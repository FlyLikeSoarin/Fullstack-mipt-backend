package edu.mipt.backend.services;

import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class YoutubeFetchService {
    public static class FetchedData {
        private FetchedData() {
        }

        public String title;
        public Long duration;
    };

    public static class InvalidURL extends Exception {
        InvalidURL(String reason) {
            super(reason);
        }
    }

    public static FetchedData fetchData(String videoURL) throws InvalidURL {
        String content = getContent(videoURL);
        String titleOpenTag = "\\\"title\\\":\\\"";
        String durationOpenTag = "\\\"lengthSeconds\\\":\\\"";
        String closeTag = "\\\",";

        FetchedData data = new FetchedData();

        if (content.contains(titleOpenTag)) {
            data.title = content.substring(
                    titleOpenTag.length() + content.indexOf(titleOpenTag),
                    content.indexOf(closeTag, content.indexOf(titleOpenTag))
            );
        } else {throw new InvalidURL("Does not contain title");}

        if (content.contains(durationOpenTag)) {
            data.duration = Long.parseLong(
                    content.substring(
                            durationOpenTag.length() + content.indexOf(durationOpenTag),
                            content.indexOf(closeTag, content.indexOf(durationOpenTag)))
            );
        } else {throw new InvalidURL("Does not contain duration");}

        return data;
    }

    private static String getContent(String videoURL) {
        String content = null;
        try {
            URLConnection connection =  new URL(videoURL).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return content;
    }
}
