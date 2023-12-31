package parser;

import scanner.ScannerFacade;
import scanner.token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.actionsPolymorphysm.AcceptAction;
import parser.actionsPolymorphysm.ReduceAction;
import parser.actionsPolymorphysm.ShiftAction;

/**
 * Created by mohammad hosein on 6/25/2015.
 */

public class ParseTable {
    private List<Map<Token, Action>> actionTable;
    private List<Map<NonTerminal, Integer>> gotoTable;
    private ScannerFacade scannerFacade;

    private void addToNonTerminals(int i, String col, Map<Integer, NonTerminal> nonTerminals) {
        String temp = col.substring(5);
        try {
            nonTerminals.put(i, NonTerminal.valueOf(temp));
        } catch (Exception e) {
        }
    }

    private void addToTerminals(int i, String col, Map<Integer, Token> terminals, ScannerFacade scannerFacade) {
        terminals.put(i, scannerFacade.getToken(col));
    }

    private boolean startsWithGoto(String col) {
        return col.startsWith("Goto");
    }

    public ParseTable(String jsonTable) throws Exception {
        scannerFacade = ScannerFacade.getInstance();
        jsonTable = jsonTable.substring(2, jsonTable.length() - 2);
        String[] Rows = jsonTable.split("\\],\\[");
        Map<Integer, Token> terminals = new HashMap<Integer, Token>();
        Map<Integer, NonTerminal> nonTerminals = new HashMap<Integer, NonTerminal>();
        Rows[0] = Rows[0].substring(1, Rows[0].length() - 1);
        String[] cols = Rows[0].split("\",\"");
        for (int i = 1; i < cols.length; i++) {
            if (startsWithGoto(cols[i])) {
                addToNonTerminals(i, cols[i], nonTerminals);
            } else {
                addToTerminals(i, cols[i], terminals, scannerFacade);
            }
        }
        actionTable = new ArrayList<Map<Token, Action>>();
        gotoTable = new ArrayList<Map<NonTerminal, Integer>>();
        for (int i = 1; i < Rows.length; i++) {
            if (i == 100) {
                int a = 1;
                a++;
            }
            Rows[i] = Rows[i].substring(1, Rows[i].length() - 1);
            cols = Rows[i].split("\",\"");
            actionTable.add(new HashMap<Token, Action>());
            gotoTable.add(new HashMap<>());
            for (int j = 1; j < cols.length; j++) {
                if (!cols[j].equals("")) {
                    if (cols[j].equals("acc")) {
                        actionTable.get(actionTable.size() - 1).put(terminals.get(j),
                                new Action(new AcceptAction(), 0));
                    } else if (terminals.containsKey(j)) {
                        // try {
                        Token t = terminals.get(j);
                        Action a = new Action(cols[j].charAt(0) == 'r' ? new ReduceAction() : new ShiftAction(),
                                Integer.parseInt(cols[j].substring(1)));
                        actionTable.get(actionTable.size() - 1).put(t, a);
                        // }catch (StringIndexOutOfBoundsException e){
                        // e.printStackTrace();
                        // }
                    } else if (nonTerminals.containsKey(j)) {
                        gotoTable.get(gotoTable.size() - 1).put(nonTerminals.get(j), Integer.parseInt(cols[j]));
                    } else {
                        throw new Exception();
                    }
                }
            }
        }
    }

    public int getGotoTable(int currentState, NonTerminal variable) {
        // try {
        return gotoTable.get(currentState).get(variable);
        // }catch (NullPointerException dd)
        // {
        // dd.printStackTrace();
        // }
        // return 0;
    }

    public Action getActionTable(int currentState, Token terminal) {
        return actionTable.get(currentState).get(terminal);
    }
}
