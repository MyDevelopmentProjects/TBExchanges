package ge.mgl.utils.constants;

import java.io.File;

/**
 * Created by MJaniko on 10/26/2016.
 */
public class Constants {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String EMPTY_OR_WRONG_TOKEN = "EMPTY_OR_WRONG_TOKEN";
    public static final String ACCESS_IS_DENIED = "access_is_denied";


    public static final String[] VALID_EXTENSIONS = {"png", "jpg", "jpeg", "mp4"};

    public static final String[] VALID_OBJECT_NAMES = {
            "USER_IMG",
            "USER_VIDEO",
            "SLIDER_IMG",
            "TEAM_IMG",
            "HOTEL_IMG",
            "HOTEL_GALLERY",
            "TOUR_IMG",
            "TOUR_GALLERY",
            "GENERAL",
    };

    public static final class UploadHelpers {
        private static final String HOME = String.format("%s%s", System.getProperty("user.home"), File.separator);
        public static final String UPLOADS = String.format("%s%s%s", HOME, "uploads", File.separator);
        public static final String USER_IMG = String.format("%s%s", UPLOADS, "user");
        public static final String SLIDER_IMG = String.format("%s%s", UPLOADS, "slider");
        public static final String TEAM_IMG = String.format("%s%s", UPLOADS, "team");
        public static final String HOTEL_IMG = String.format("%s%s", UPLOADS, "hotel");
        public static final String HOTEL_GALLERY = String.format("%s%s", UPLOADS, "hotelgallery");
        public static final String TOUR_IMG = String.format("%s%s", UPLOADS, "tour");
        public static final String TOUR_GALLERY = String.format("%s%s", UPLOADS, "tourgallery");
    }

    public static final class ErrorCodes {
        public class ErrorResponse {
            public static final String ACCESS_IS_DENIED = "access_is_denied";
            public static final String UNKNOWN = "UNKNOWN";
            public static final String DUPLICATE_RECORD = "DUPLICATE_RECORD";
            public static final String RECORD_IS_USED_IN_OTHER_TABLES = "RECORD_IS_USED_IN_OTHER_TABLES";
            public static final String PERSISTENCE_EXCEPTION = "javax.persistence.PersistenceException";
        }
    }

    public static final class ControllerCodes {
        public static final String STRING_EMPTY = "";
        public static final String UNKNOWN = "UNKNOWN";
        public static final String PAGE_NUMBER = "pageNumber";
        public static final String PAGE_NUMBER_DEFAULT_VALUE = "0";
        public static final String PAGE_SIZE_DEFAULT_VALUE = "10";
        public static final String IS_ASCENDING_DEFAULT_VALUE = "true";

        public static final String SLASH = "/";
        public static final String LIST = "list";
        public static final String LAYOUT = "layout";
        public static final String PUT = "put";
        public static final String DELETE = "delete";
        public static final String UPDATE = "update";
        public static final String KEY = "key";
        public static final String VALUE = "value";
    }
}