public class Cell {
    LayoutState state;


    public Cell() {
        this.state = LayoutState.BLOCKED;
    }

    public void setStatePassage() {
        this.state = LayoutState.PASSAGE;
    }

    public LayoutState getState() {
        return state;
    }
}
