package parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.util.Calendar.*;


public class Lesson {
    public static final int EVERY_WEEK = 0;
    public static final int UPPER_WEEK = 1;
    public static final int LOWER_WEEK = 2;

    private String groupNumber;
    private Integer dayOfWeek;
    private String lecture;
    private String instructor;
    private String room;

    private Integer fromHours;
    private Integer fromMinutes;
    private Integer toHours;
    private Integer toMinutes;
    private String groupName;
    private Integer week = EVERY_WEEK;


    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getDay() {
        switch (dayOfWeek.intValue()) {
            case MONDAY:
                return "Пн";
            case TUESDAY:
                return "Вт";
            case WEDNESDAY:
                return "Ср";
            case THURSDAY:
                return "Чт";
            case FRIDAY:
                return "Пт";
            case SATURDAY:
                return "Сб";
            case SUNDAY:
                return "Вс";
            default:
                return "";
        }
    }

    public void setDay(String day) {
        switch (day) {
            case "ПОНЕДЕЛЬНИК":
                this.dayOfWeek = MONDAY;
                break;
            case "ВТОРНИК":
                this.dayOfWeek = Calendar.TUESDAY;
                break;
            case "СРЕДА":
                this.dayOfWeek = Calendar.WEDNESDAY;
                break;
            case "ЧЕТВЕРГ":
                this.dayOfWeek = Calendar.THURSDAY;
                break;
            case "ПЯТНИЦА":
                this.dayOfWeek = Calendar.FRIDAY;
                break;
            case "СУББОТА":
                this.dayOfWeek = Calendar.SATURDAY;
                break;
            case "ВОСКРЕСЕНЬЕ":
                this.dayOfWeek = Calendar.SUNDAY;
                break;
            default:
                throw new IllegalArgumentException("Unknown day of week:" + day);
        }
    }

    public String getHours() {
        return zs(fromHours) + ":" + zs(fromMinutes) + "-" + zs(toHours) + ":" + zs(toMinutes);
    }

    private String zs(Integer n) {
        return String.format("%02d", n);
    }

    private Calendar nextDay() {
        Calendar date = Calendar.getInstance();

        while (date.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
            date.add(Calendar.DATE, 1);
        }

        return date;
    }

    public String getNextFrom() {
        Calendar date = nextDay();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        return dateFormat1.format(date.getTime()) + "T" + zs(fromHours) + zs(fromMinutes) + "00"; //20161106T100000
    }

    public String getNextTo() {
        Calendar date = nextDay();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        return dateFormat1.format(date.getTime()) + "T" + zs(toHours) + zs(toMinutes) + "00"; //20161106T100000
    }

    public void setHours(String hours) {
        //this.hours = hours;
        if (hours == null) {
            //System.out.println("NULL HOURS"); //todo remove STUB!!!! DEBUG null hours
            return;
        }
        //parse from and to hours "08.30-10.00"
        String[] fromTo = hours.split("-");
        String from = fromTo[0];
        int point = from.indexOf(".");
        if (point != -1) {
            this.fromHours = Integer.valueOf(from.substring(0, point).trim());
            this.fromMinutes = Integer.valueOf(from.substring(point + 1).trim());
        }
        String to = fromTo[1];
        point = to.indexOf(".");
        if (point != -1) {
            this.toHours = Integer.valueOf(to.substring(0, point).trim());
            this.toMinutes = Integer.valueOf(to.substring(point + 1).trim());
        }
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getFromHours() {
        return fromHours;
    }

    public void setFromHours(Integer fromHours) {
        this.fromHours = fromHours;
    }

    public Integer getFromMinutes() {
        return fromMinutes;
    }

    public void setFromMinutes(Integer fromMinutes) {
        this.fromMinutes = fromMinutes;
    }

    public Integer getToHours() {
        return toHours;
    }

    public void setToHours(Integer toHours) {
        this.toHours = toHours;
    }

    public Integer getToMinutes() {
        return toMinutes;
    }

    public void setToMinutes(Integer toMinutes) {
        this.toMinutes = toMinutes;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return getDay() + " " + getHours() + " " + getGroupNumber() + " " + getLecture() + " " + getInstructor() + " " + getRoom();
    }
}