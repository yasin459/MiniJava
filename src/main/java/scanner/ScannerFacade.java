package scanner;

import scanner.token.Token;

public class ScannerFacade {
    private static ScannerFacade scannerFacade;

    private ScannerFacade() {

    }

    public static ScannerFacade getInstance() {
        if (scannerFacade == null)
            scannerFacade = new ScannerFacade();
        return scannerFacade;
    }

    public Token getToken(String str) {
        return new Token(Token.getTyepFormString(str), str);
    }
}
