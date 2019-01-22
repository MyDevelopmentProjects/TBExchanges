package ge.mgl.utils;

import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by MJaniko on 4/30/2017.
 */
public class JSGeneratorUtil {

    private static final String JS_PATTERN = "<script src=\"{name}\"></script>";

    public static void GenerateJS(Model model, String name){
        model.addAttribute("importJS", JS_PATTERN.replace("{name}", name));
    }

    public static void GenerateJS(Model model, List<String> names){
        StringBuilder stringBuilder = new StringBuilder();
        for(String s : names){
            stringBuilder.append(JS_PATTERN.replace("{name}", s));
        }
        model.addAttribute("importJS", stringBuilder.toString());
    }
}
