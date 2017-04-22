package ir.minoo96.Utility;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.Html;
import android.text.format.DateUtils;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ir.minoo96.Items.Candidate;

public class Utilities {

    public static Candidate findCandidateItem(int candidateId) {
        for (Candidate item : Variables.candidates) {
            if (item.getId() == candidateId) {
                return item;
            }
        }
        Candidate nullCandidate = new Candidate();
        nullCandidate.setImage("http://test.minoo96.ir/Theme/Images/Profiles/person.jpg");
        return nullCandidate;
    }

    public static String getSerial(Context context) {
        String androidId = "";

        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(context, "user");
        androidId = sharedPreferenceHelper.getString("deviceid", "");

        if (androidId.length() == 0) {
            try {
                androidId += Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (androidId.equals("9774d56d682e549c"))
                androidId = "";

            try {
                androidId += Build.SERIAL;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (androidId.length() == 0 || androidId.equals("unknown")) {
                androidId = Installation.id(context);
            }

            sharedPreferenceHelper.setString("deviceid", androidId);
        }

        return androidId;
    }

    public static String getTextFromHtml(String html) {
        return Html.fromHtml(html).toString().trim();
    }

    public static String toPersianDate(String date) {
        String timestamp = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date newDate;
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            newDate = new Date();
        }

        try {
            if (DateUtils.isToday(newDate.getTime()) && newDate.getHours() == new Time(System.currentTimeMillis()).getHours()) {
                Period period = new Period(newDate.getTime(), System.currentTimeMillis());
                PeriodFormatter formatter = new PeriodFormatterBuilder()
                        .appendMinutes().appendSuffix(" دقیقه پیش").printZeroNever().toFormatter();
                timestamp = formatter.print(period);
            } else if (DateUtils.isToday(newDate.getTime())) {
                Period period = new Period(newDate.getTime(), System.currentTimeMillis());
                PeriodFormatter formatter = new PeriodFormatterBuilder()
                        .appendHours().appendSuffix(" ساعت پیش").printZeroNever().toFormatter();
                timestamp = formatter.print(period);
            } else {
                SolarCalendar sc = new SolarCalendar(newDate);
                timestamp = sc.date + " " + getMonthName(sc.month) + " " + sc.year;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return timestamp;
    }

    public static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "فروردین";
            case 2:
                return "اردیبهشت";
            case 3:
                return "خرداد";
            case 4:
                return "تیر";
            case 5:
                return "مرداد";
            case 6:
                return "شهریور";
            case 7:
                return "مهر";
            case 8:
                return "آبان";
            case 9:
                return "آذر";
            case 10:
                return "دی";
            case 11:
                return "بهمن";
            case 12:
                return "اسفند";
            default:
                return "فروردین";
        }
    }
}
