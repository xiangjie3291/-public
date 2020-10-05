import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GetSym {
    private StringBuilder File_text;
    private String text;
    private int index;
    private char Char;
    private StringBuilder token;
    private String symbol;
    private HashMap<String,String> word;
    public GetSym(){
        this.File_text=new StringBuilder();
        this.token=new StringBuilder();
        this.text="";
        this.index=0;
        this.Char=' ';
        this.symbol=symbol;
        this.word=new HashMap<String,String>();
        this.word.put("BEGIN", "Begin");
        this.word.put("END", "End");
        this.word.put("FOR", "For");
        this.word.put("IF", "If");
        this.word.put("THEN", "Then");
        this.word.put("ELSE", "Else");
    }


    public void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                this.File_text.append(' ');
                this.File_text.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getText () {
        return this.File_text.toString();
    }

    public boolean getchar(){
        if(index<text.length()){
            Char=text.charAt(index++);
            return true;
        }
        Char=' ';
        return false;
    }
    public void clearToken(){
        token.delete(0,token.length());
    }
    public boolean isSpace(){
        if(Char==' '||Char=='\r'){
            return true;
        }
        return false;
    }
    public boolean isNewline(){
        if(Char=='\n'){
            return true;
        }
        return false;
    }
    public boolean isTab(){
        if(Char=='\t'){
            return true;
        }
        return false;
    }
    public boolean isDigit(){
        if(Char>='0'&&Char<='9'){
            return true;
        }
        return false;
    }
    public boolean isLetter(){
        if((Char>='a'&&Char<='z')||(Char>='A'&&Char<='Z')){
            return true;
        }
        return false;
    }
    public boolean isColon(){
        if(Char==':'){
            return true;
        }
        return false;
    }
    public boolean isPlus(){
        if(Char=='+'){
            return true;
        }
        return false;
    }
    public boolean isMinus(){
        if(Char=='-'){
            return true;
        }
        return false;
    }
    public boolean isStar(){
        if(Char=='*'){
            return true;
        }
        return false;
    }
    public boolean isComma(){
        if(Char==','){
            return true;
        }
        return false;
    }
    public boolean isLpar(){
        if(Char=='('){
            return true;
        }
        return false;
    }
    public boolean isRpar(){
        if(Char==')'){
            return true;
        }
        return false;
    }
    public boolean isEqu(){
        if(Char=='='){
            return true;
        }
        return false;
    }
    public void catToken(){
        token.append(Char);
    }
    public void retract(){
        index--;
    }
    public String reserver(){
        if(word.containsKey(token.toString())){
            return word.get(token.toString());
        }
        return "0";
    }
    public int transNum(StringBuilder token){
        return Integer.parseInt(token.toString());
    }
    public void error(){
        System.out.println("error");
    }
    public int lex_analysis(){
        clearToken();
        while(index<text.length()){
           // System.out.println(text.length());
            //System.out.println(index);
            clearToken();
            while(getchar()&&(isSpace()||isNewline()||isTab())){

            }
            if(isLetter()){
                while((isLetter()||isDigit())){
                    catToken();getchar();
                }
                if(index<text.length()) {
                    retract();
                }
                String resultValue=reserver();
                if(resultValue.equals("0"))
                    symbol="Ident("+token+")";
                else
                    symbol=resultValue;
            }
            else if(isDigit()){
                while(isDigit()){
                    catToken();
                    getchar();
                }
                if(index<text.length()) {
                    retract();
                }
                int num=transNum(token);
                symbol="Int("+num+")";
            }
            else if(isColon()){
                getchar();
                if(isEqu()) symbol="Assign";
                else{
                    if(index<text.length()) {
                        retract();
                    }
                    symbol="Colon";
                }
            }
            else if(isPlus()){
                symbol="Plus";
            }
            else if(isStar()){
                symbol="Star";
            }
            else if(isLpar()){
                symbol="LParenthesis";
            }
            else if(isRpar()){
                symbol="RParenthesis";
            }
            else if(isComma()){
                symbol="Comma";
            }
            else{
                symbol="Unknown";
            }
            System.out.println(symbol);
        }
        return 0;
    }

    public static void main(String[] args) {
        String filename = "D:/hello.txt";
        GetSym temp=new GetSym();
        temp.readFileByLines(filename);
        temp.text=temp.getText();
        System.out.println(temp.text);
        System.out.println();
        temp.lex_analysis();
    }
}
