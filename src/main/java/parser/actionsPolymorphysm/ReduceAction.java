package parser.actionsPolymorphysm;

public class ReduceAction extends ActionPolymorphysm {
    @Override
    public String toString() {
        return "reduce";
    }

    @Override
    public String actionToString(int num) {
        return "r" + num;
    }
}
