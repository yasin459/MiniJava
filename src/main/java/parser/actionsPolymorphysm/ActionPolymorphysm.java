package parser.actionsPolymorphysm;

public abstract class ActionPolymorphysm {
    public abstract String toString();

    public String actionToString(int num) {
        return this.toString() + num;
    }
}
