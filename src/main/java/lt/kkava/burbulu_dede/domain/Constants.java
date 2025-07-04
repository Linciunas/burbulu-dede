package lt.kkava.burbulu_dede.domain;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Constants {
    public static final ZoneId ZONE_ID_LONDON = ZoneId.of("Europe/London");
    public static final ZoneId ZONE_ID_VILNIUS = ZoneId.of("Europe/Vilnius");
    public static final ZoneId ZONE_ID_BERLIN = ZoneId.of("Europe/Berlin");
    public static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");

    public static final Locale LOCALE_VILNIUS = Locale.of("LT");

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_FORMATTER_yyMMdd = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter DATE_FORMATTER_HHmm = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter DATE_FORMATTER_HHmmss = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd_HHmm = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
    public static final DateTimeFormatter DATE_FORMATTER_yyMMdd_HHmm = DateTimeFormatter.ofPattern("yyMMdd HHmm");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd_HHmmss = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMddHHmmssSSSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS");
    public static final DateTimeFormatter DATE_FORMATTER_ddMMyy_HHmmss = DateTimeFormatter.ofPattern("ddMMyy_HHmmss");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd_HHmmss_file = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd_dot_HHmmss_file = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd_dot_HHmmssSSS_file = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmssSSS");

    public static final String EMAILER = "emailer";
    public final static String DEFAULT_USER = "ECO1";

    public static final String SESSION_ORDER = "order";
}
