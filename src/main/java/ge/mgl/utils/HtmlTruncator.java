package ge.mgl.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mikheil on 7/12/2017.
 */
public class HtmlTruncator extends SimpleTagSupport {

    private String text;
    private int    length;

    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();
        try{
            out.write(truncateHtmlWords(this.text, this.length));
        }  catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private static String truncateHtmlWords(String text, int max_length) {
        String input = text.trim();
        if (max_length > input.length()) {
            return input;
        }
        if (max_length < 0) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        /**
         * Pattern pattern_opentag = Pattern.compile("(<[^/].*?[^/]>).*");
         * Pattern pattern_closetag = Pattern.compile("(</.*?[^/]>).*"); Pattern
         * pattern_selfclosetag = Pattern.compile("(<.*?/>).*");*
         */
        String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
        Pattern pattern_overall = Pattern.compile(HTML_TAG_PATTERN + "|" + "\\s*\\w*\\s*");
        Pattern pattern_html = Pattern.compile("(" + HTML_TAG_PATTERN + ")" + ".*");
        Pattern pattern_words = Pattern.compile("(\\s*\\w*\\s*).*");
        int characters = 0;
        Matcher all = pattern_overall.matcher(input);
        while (all.find()) {
            String matched = all.group();
            Matcher html_matcher = pattern_html.matcher(matched);
            Matcher word_matcher = pattern_words.matcher(matched);
            if (html_matcher.matches()) {
                output.append(html_matcher.group());
            } else if (word_matcher.matches()) {
                if (characters < max_length) {
                    String word = word_matcher.group();
                    if (characters + word.length() < max_length) {
                        output.append(word);
                    } else {
                        output.append(word.substring(0,
                                (max_length - characters) > word.length()
                                        ? word.length() : (max_length - characters)));
                    }
                    characters += word.length();
                }
            }
        }
        return output.toString();
    }

}
