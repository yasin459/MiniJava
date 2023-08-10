package parser.actionsPolymorphysm;

public class ShiftAction extends ActionPolymorphysm {
    @Override
    public String toString() {
        return "shift";
    }

    @Override
    public String actionToString(int num) {
        return "s" + num;
    }
}
