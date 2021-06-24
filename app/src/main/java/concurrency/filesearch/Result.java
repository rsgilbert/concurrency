package concurrency.filesearch;

/**
 * Holds the result of file search
 */
public class Result {

    private boolean found;
    private String path;

    public Result() {
        new Result(false, "");
    }

    public Result(
            boolean found,
            String path
    ) {
        this.found = found;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Found: " + found + ", Path: " + path;
    }
}
