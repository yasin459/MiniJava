package parser;

import parser.actionsPolymorphysm.ActionPolymorphysm;

public class Action {
    public ActionPolymorphysm action;
    //if action = shift : number is state
    //if action = reduce : number is number of rule
    public int number;

    public Action(ActionPolymorphysm action, int number) {
        this.action = action;
        this.number = number;
    }

    public String toString() {
        return action.actionToString(number);
    }
}

