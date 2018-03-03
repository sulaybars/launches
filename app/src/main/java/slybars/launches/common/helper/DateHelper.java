package slybars.launches.common.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by slybars on 03/03/2018.
 */

public class DateHelper {

    private static DateHelper dateHelper;

    public static Locale locale = new Locale("tr", "TR");
    public static SimpleDateFormat serviceDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale);
    public static SimpleDateFormat shortDateFormat = new SimpleDateFormat("d MMMM yyyy", locale);

    private DateHelper() {

    }

    public static DateHelper getInstance() {
        if (dateHelper == null) {
            dateHelper = new DateHelper();
        }
        return dateHelper;
    }


    /**
     * This method used for converting serviceDateFormat to shortDateFormat.
     *
     * @param date String serviceDateFormat
     * @return String shortDateFormat
     */
    public String convertServiceDateToShortDate(String date) {
        try {
            Date longDate = serviceDateFormat.parse(date);
            return shortDateFormat.format(longDate);
        } catch (Exception e) {
            return "";
        }
    }


}
