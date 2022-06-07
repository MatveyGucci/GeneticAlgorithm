import java.util.Base64;

public class FourthStageDecoder {
    String toDecode = "G0IFOFVMLRAPI1QJbEQDbFEYOFEPJxAfI10JbEMFIUAAKRAfOVIfOFkYOUQFI15ML1kcJFUeYhA4IxAeKVQZL1VMOFgJbFMDIUAAKUgFOElMI1ZMOFgFPxADIlVMO1VMO1kAIBAZP1VMI14ANRAZPEAJPlMNP1VMIFUYOFUePxxMP19MOFgJbFsJNUMcLVMJbFkfbF8CIElMfgZNbGQDbFcJOBAYJFkfbF8CKRAeJVcEOBANOUQDIVEYJVMNIFwVbEkDORAbJVwAbEAeI1INLlwVbF4JKVRMOF9MOUMJbEMDIVVMP18eOBADKhALKV4JOFkPbFEAK18eJUQEIRBEO1gFL1hMO18eJ1UIbEQEKRAOKUMYbFwNP0RMNVUNPhlAbEMFIUUALUQJKBANIl4JLVwFIldMI0JMK0INKFkJIkRMKFUfL1UCOB5MH1UeJV8ZP1wVYBAbPlkYKRAFOBAeJVcEOBACI0dAbEkDORAbJVwAbF4JKVRMJURMOF9MKFUPJUAEKUJMOFgJbF4JNERMI14JbFEfbEcJIFxCbHIJLUJMJV5MIVkCKBxMOFgJPlVLPxACIxAfPFEPKUNCbDoEOEQcPwpDY1QDL0NCK18DK1wJYlMDIR8II1MZIVUCOB8IYwEkFQcoIB1ZJUQ1CAMvE1cHOVUuOkYuCkA4eHMJL3c8JWJffHIfDWIAGEA9Y1UIJURTOUMccUMELUIFIlc=";

    public void lengthGetter() {
        int repetition = 0;
        byte bytes[] = Base64.getDecoder().decode(toDecode);
        toDecode = "";
        for (int o = 1; o < bytes.length; o++) {
            toDecode += (char) bytes[o];
        }
        String shiftedText = toDecode;
        String ss = "";
        for (int i = 1; i < 100; i++) {

            for (int j = 0; j < toDecode.length(); j++) {
                if (toDecode.charAt(j) == shiftedText.charAt(j)) {
                    repetition++;
                }

            }
            ss = shiftedText.substring(1) + shiftedText.charAt(0);
            shiftedText = ss;
            System.out.println(i + "th iteration : " + repetition);
            repetition = 0;
        }
    }

    public void fourthStageDecode() {
        String alphabet = "LlAaBb0CcDdEeFfGgHhIiJjKkMmNnOoPpQqRrSsTtUuVvWwXxYyZz,! .123456789";
        char c1,c2,c3;
        byte bytes[] = Base64.getDecoder().decode(toDecode);
        toDecode = new String(bytes);
        String test ="";
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++){
                for (int k = 0; k < alphabet.length(); k++){
                    for (int z =0; z+3<= toDecode.length(); z+=3)
                    {
                        c1 = (char) (toDecode.charAt(z) ^ alphabet.charAt(i));
                        c2 = (char) (toDecode.charAt(z + 1) ^ alphabet.charAt(j));
                        c3 = (char) (toDecode.charAt(z + 2) ^ alphabet.charAt(k));
                            finalString.append(c1).append(c2).append(c3);
                            test = finalString.toString();
                    }
                            if (test.contains("Write")) {
                                System.out.println(finalString.toString());
                                finalString.delete(0, finalString.length());
                            }
                }

            }
        }
    }
}

class FourthMain {
    public static void main(String[] args) {
        FourthStageDecoder fourthStageDecoder = new FourthStageDecoder();
        fourthStageDecoder.fourthStageDecode();

    }
}
