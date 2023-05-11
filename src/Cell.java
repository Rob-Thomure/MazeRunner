
public class Cell {
    LayoutState state;


    public Cell() {
        this.state = LayoutState.BLOCKED;
    }

    public void setStatePassage() {
        this.state = LayoutState.PASSAGE;
    }

    public void setStateTraveled() {
        this.state = LayoutState.TRAVELED;
    }

    public LayoutState getState() {
        return state;
    }
}
