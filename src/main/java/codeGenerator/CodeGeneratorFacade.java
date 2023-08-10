package codeGenerator;

import java.util.HashMap;
import java.util.Map;

import scanner.token.Token;

public class CodeGeneratorFacade {

    private Memory memory;
    private CodeGenerator codeGenerator;
    private Map<String, Address> keyWords;

    public CodeGeneratorFacade() {

    }

    public CodeGeneratorFacade bulidCodeGeneratorFacade(Memory memory) {
        keyWords = new HashMap<>();
        keyWords.put("true", new Address(1, varType.Bool, TypeAddress.Imidiate));
        keyWords.put("false", new Address(0, varType.Bool, TypeAddress.Imidiate));
        this.setMemory(memory);
        return this;
    }

    public CodeGeneratorFacade createCodeGenerator() {
        this.setCodeGenerator(new CodeGenerator());
        return this;
    }

    public Address getKeywordAddress(String keywordName) {
        return keyWords.get(keywordName);
    }

    public int getDateAddress() {
        return memory.getDateAddress();
    }

    public Address getBoolmidiateAddress(int num) {
        return new Address(num, varType.Bool, TypeAddress.Imidiate);
    }

    public void setCodeGenerator(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public void semanticFunction(int func, Token next) {
        codeGenerator.semanticFunction(func, next);
    }

    public void printMemory() {
        codeGenerator.printMemory();
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
}