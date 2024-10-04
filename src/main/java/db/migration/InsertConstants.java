package db.migration;

public final class InsertConstants {

    private InsertConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final int COMMENTS_COUNT = 2;
    public static final int MIN_QUESTION_DIFFICULTY = 1;
    public static final int MAX_QUESTION_DIFFICULTY = 5;
    public static final int QUESTIONS_COUNT = 50;
    public static final int TAGS_COUNT = 30;
    public static final int TAGS_FOR_QUESTION_COUNT = 4;
    public static final int ANSWERS_FOR_QUESTION_COUNT = 3;
}
