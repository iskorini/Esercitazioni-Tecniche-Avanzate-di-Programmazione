package mutation.example;

/**
 * Created by Federico on 12/10/2016.
 */
public class Timeline {

    static public final int DEFAULT_FETCH_COUNT = 10;

    private int fetchCount;

    public Timeline() {
        this.fetchCount = DEFAULT_FETCH_COUNT;
    }


    public void setFetchCount(int fetchCount) {
        if (fetchCount<=0) {
            throw new IllegalArgumentException("must be positive");
        }
        this.fetchCount = fetchCount;
    }

    public int getFetchCount() {
        return fetchCount;
    }
}
