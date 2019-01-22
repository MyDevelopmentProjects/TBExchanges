package ge.mgl.utils;

import ge.mgl.entities.FUser;
import ge.mgl.security.SpringSecurityUser;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GeneralUtil {

    public static String generateString(int length) {
        if (length < 5) length = 5;
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = chars.charAt(rng.nextInt(chars.length()));
        }
        return String.valueOf(text);
    }

    public static Integer generateRandom() {
        Random r = new Random();
        int lowerBound = 1000;
        int upperBound = 9999;
        return r.nextInt(upperBound - lowerBound) + lowerBound;
    }


    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    public static <T> List<T> createListFrom(T obj) {
        List<T> list = new ArrayList<>();
        list.add(obj);
        return list;
    }

    public static List<String> getHashTags(String tweetText) {

        List<String> hashTags = new ArrayList<>();

        String patternStr = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(tweetText);
        String result = "";

        // Search for Hashtags
        while (matcher.find()) {
            result = matcher.group();
            result = result.replace(" ", "");
            String search = result.replace("#", "");
            hashTags.add(result);
            tweetText = tweetText.replace(result, "");
        }

        return hashTags;
    }

    public static String buildPoint(String lat, String lng) {
        return lat + "," + lng;
    }

    public static String encodeMD5(String text) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(text.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {

        }
        return null;
    }

    public static Date str2Date(String str) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static int getDaysDifference(long t1, long t2) {
        if (t1 == t2) {
            return 0;
        }
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }

    public static void reloadUserData(FUser user) {
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            grantedAuthorities.addAll(user.getRole().getPermissions().stream().map(
                    permission -> new SimpleGrantedAuthority(permission.getName())
            ).collect(Collectors.toList()));

            // Load User Role
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

            SpringSecurityUser securityUser = new SpringSecurityUser(user, grantedAuthorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(securityUser, securityUser.getPassword(), grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public static Date long2Date(Long mlscds) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(mlscds);
        return c.getTime();
    }

    public static RequestResponse removalSuccess() {
        return new RequestResponse(true, "Object was removed successfully", 200);
    }

    public static RequestResponse RequestSuccess() {
        return new RequestResponse(true);
    }

    public static RequestResponse RequestSuccess(String message) {
        return new RequestResponse(true, message);
    }

    public static RequestResponse RequestSuccess(String message, Integer code) {
        return new RequestResponse(true, message, code);
    }

    public static RequestResponse RequestError() {
        return new RequestResponse(false);
    }

    public static RequestResponse RequestError(String message) {
        return new RequestResponse(false, message);
    }

    public static RequestResponse RequestError(String message, Integer code) {
        return new RequestResponse(false, message, code);
    }

    public static String date2String(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static PaginationAndFullSearchQueryResult emptyResult() {
        PaginationAndFullSearchQueryResult result = new PaginationAndFullSearchQueryResult();
        result.setResults(new ArrayList());
        result.setSuccess(true);
        result.setCode(200);
        return result;
    }

    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    public static String hashWithUUID() {
        return UUID() + generateString(64);
    }

    public static String getActivationCode(String email, String hash, String username) {
        StringBuilder sb = new StringBuilder();
        sb.append("Feedc - To activate your account, please click ");
        sb.append("<a href='http://18.195.127.92/sms/confirm");
        sb.append("?username=" + username + "&email=" + email + "&accessToken=" + hash + "' target='blank'>here</a>");
        return sb.toString();
    }

    public static String generateHash(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()_+-=.,/';:?><~*/-+";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
