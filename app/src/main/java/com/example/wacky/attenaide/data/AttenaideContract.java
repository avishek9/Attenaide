package com.example.wacky.attenaide.data;

import android.provider.BaseColumns;

/**
 * Created by wacky on 3/5/17.
 */

public class AttenaideContract {
    public static final class AttenaideEntry implements BaseColumns {
        public static final String STUDENT_TABLE_NAME = "students";
        public static final String COLUMN_ENROLLMENT_NO = "enrollNo";
        public static final String COLUMN_CLASS_ID = "classId";
        public static final String COLUMN_NAME = "name";
        public static final String CLASS_TABLE_NAME = "class";
        public static final String COLUMN_CLASS_NAME = "className";
        public static final String ATTEN_TABLE_NAME = "attendance";
        public static final String COLUMN_LECTURE_ID = "lectureId";
        public static final String COLUMN_STUDENT_ID = "studentId";
        public static final String COLUMN_STATUS = "status";
        public static final String LECTURE_TABLE_NAME = "lecture";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_LECTURE_NO = "lectureNo";
    }
}
