import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class GeneticAlgorithm {
    String task = "EFFPQLEKVTVPCPYFLMVHQLUEWCNVWFYGHYTCETHQEKLPVMSAKSPVPAPVYWMVHQLUSPQLYWLASLFVWPQLMVHQLUPLRPSQLULQESPBLWPCSVRVWFLHLWFLWPUEWFYOTCMQYSLWOYWYETHQEKLPVMSAKSPVPAPVYWHEPPLUWSGYULEMQTLPPLUGUYOLWDTVSQETHQEKLPVPVSMTLEUPQEPCYAMEWWYTYWDLUULTCYWPQLSEOLSVOHTLUYAPVWLYGDALSSVWDPQLNLCKCLRQEASPVILSLEUMQBQVMQCYAHUYKEKTCASLFPYFLMVHQLUPQLHULIVYASHEUEDUEHQBVTTPQLVWFLRYGMYVWMVFLWMLSPVTTBYUNESESADDLSPVYWCYAMEWPUCPYFVIVFLPQLOLSSEDLVWHEUPSKCPQLWAOKLUYGMQEUEMPLUSVWENLCEWFEHHTCGULXALWMCEWETCSVSPYLEMQYGPQLOMEWCYAGVWFEBECPYASLQVDQLUYUFLUGULXALWMCSPEPVSPVMSBVPQPQVSPCHLYGMVHQLUPQLWLRPOEDVMETBYUFBVTTPENLPYPQLWLRPTEKLWZYCKVPTCSTESQPBYMEHVPETCMEHVPETZMEHVPETKTMEHVPETCMEHVPETT";
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String text;
    Map<String, Double> standard = percentNgram(ngram());

    public GeneticAlgorithm() throws IOException {
    }


    public void geneticAlgoriyhm() {
        Sample[] samples = new Sample[1000];
        Sample[] fifteenPercents = new Sample[(int) (1000 * 0.15d)];
        Sample[] bufferSamples = new Sample[1000];
        Sample sample = null;
        String s1;
        Sample[] littleBuffer = new Sample[2];
        s1 = codeRandomizer();
        Sample[] buffer = new Sample[20];
        double suspicious;
        int randomBuffer = 0;
        Random random = new Random();
        double d1;
        for (int n = 0; n < 2; n++)
            for (int i = 0; i < samples.length; i++) {
                s1 = codeRandomizer();
                d1 = fitnessCounter(percentNgram(ngramCounter(decoder(s1))));
                sample = new Sample(s1, d1);
                samples[i] = sample;
            }
        String Sasha = null;
        String Sasha1 = null;

        for (int q = 0; q < 1000; q++) {
            bubbleSort(samples);
            for (int i = 0; i < samples.length * 0.15d; i++) {
                fifteenPercents[i] = samples[i];
            }
            for (int i = 0; i < 500; i++) {
                for (int n = 0; n < 2; n++) {
                    for (int j = 0; j < buffer.length; j++) {
                        randomBuffer = random.nextInt(samples.length);
                        buffer[j] = samples[randomBuffer];
                    }
                    littleBuffer[n] = sampleFromBuffer(buffer);
                }
                bubbleSort(buffer);

                Sasha = uniformCrossOver(littleBuffer[0].code, littleBuffer[1].code);
                Sasha1 = uniformCrossOver(littleBuffer[1].code, littleBuffer[0].code);
                bufferSamples[i * 2] = new Sample(Sasha, fitnessCounter(percentNgram(ngramCounter(decoder(Sasha)))));
                bufferSamples[i * 2 + 1] = new Sample(Sasha1, fitnessCounter(percentNgram(ngramCounter(decoder(Sasha1)))));


            }
            bubbleSort(bufferSamples);
            samples = bufferSamples.clone();
            for (int i = 0; i < samples.length * 0.15d; i++) {
                samples[(int) ((samples.length - samples.length * 0.15d) + i)] = fifteenPercents[i];
            }
            samplesToConsole(samples);
        }
    }


    public Sample sampleFromBuffer(Sample[] buffer) {
        double d1, suspicious;
        Random random = new Random();
        Sample sample = null;

        for (int j = 0; j < buffer.length; j++) {
            d1 = random.nextDouble();
            suspicious = 0.75 * Math.pow(0.25, j);
            if (d1 <= suspicious) {
                sample = buffer[j];
                break;
            } else {
                sample = buffer[0];
            }
        }
        return sample;
    }

    public void bubbleSort(Sample[] samples) {
        Sample sample;
        for (int i = 0; i < samples.length; i++) {
            for (int j = 0; j < i; j++) {
                if (samples[i].grade < samples[j].grade) {
                    sample = samples[i];
                    samples[i] = samples[j];
                    samples[j] = sample;
                }
            }
        }
        //samplesToConsole(samples);
    }

    public void samplesToConsole(Sample[] samples) {
        for (int i = 0; i < samples.length; i++) {
            System.out.println(samples[i].code + " " + samples[i].grade + " " + i);
        }
    }

    public void smartShuffle(Sample[] samples) {
        Sample sample;
        for (int i = 0; i < samples.length - 2; i++) {
            sample = samples[i];
            samples[i] = samples[i + 2];
            samples[i + 2] = sample;
        }
    }

    public double fitnessCounter(Map<String, Double> percentMap) {
        double coeficient = 0;
        for (Map.Entry<String, Double> i : percentMap.entrySet()) {
            if (standard.containsKey(i.getKey())) {
                coeficient += Math.abs((Math.log(standard.get(i.getKey())) / Math.log(2)) * i.getValue());
            }
        }
        return coeficient;
    }


    public Map<String, Double> percentNgram(Map<String, Double> map) {

        double sum = 0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            sum += entry.getValue();
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            map.put(entry.getKey(), entry.getValue() / sum * 100);
        }
        return map;

    }


    public String decoder(String key) { // Decode text by key

        String stub = task;
        StringBuilder finalText = new StringBuilder();
        ArrayList<String> arrays = new ArrayList<>();
        int flag = 0;
        for (int i = 0; i < key.length(); i++) {
            arrays.add(stub.replaceAll(String.valueOf(key.charAt(i)), String.valueOf(alphabet.charAt(i))));
            //System.out.println(arrays.get(i));
        }
        for (int i = 0; i < task.length(); i++) {
            for (int j = 0; j < arrays.size(); j++) {

                if (arrays.get(j).charAt(i) != task.charAt(i)) {
                    finalText.append(arrays.get(j).charAt(i));
                } else if (flag == arrays.size() - 1) {
                    finalText.append(arrays.get(j).charAt(i));
                } else {
                    flag++;
                }

            }

            flag = 0;
        }
        //System.out.println(finalText);
        return finalText.toString();

    }


    public Map<String, Double> ngramCounter(String text) {

        Map<String, Double> map = new HashMap<>();
        String stub = "";
        for (int i = 0; i + 3 < text.length(); i++) {
            stub = text.substring(i, i + 3);
            if (map.containsKey(stub)) {
                map.put(stub, map.get(stub) + 1.0);
            } else {
                map.put(stub, 1.0);
            }

        }

        return map;
    }

    public HashMap<String, Double> ngram() throws FileNotFoundException {
        HashMap<String, Double> map = new HashMap<>();
        File file = new File("src/trigrams.txt");
        Scanner scanner = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        double d1;
        while (scanner.hasNextLine()) {
            builder= new StringBuilder(scanner.nextLine());
            d1 = Double.parseDouble(builder.toString().replaceAll("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]",""));
            map.put(builder.toString().replaceAll("[1234567890 ]",""), d1);
        }
        return map;

    }

    public String convertText() throws IOException {
        char ch = 0;
        StringBuilder finalResult = new StringBuilder();
        FileReader fileReader = new FileReader("src/Frankenstein.txt");
        int stub;
        while ((stub = fileReader.read()) != -1) {
            ch = (char) stub;
            finalResult.append(ch);
        }
        finalResult = new StringBuilder(finalResult.toString().replaceAll("[^a-zA-Z]", ""));
        finalResult = new StringBuilder(finalResult.toString().replaceAll(System.getProperty("line.separator"), ""));
        finalResult = new StringBuilder(finalResult.toString().replaceAll(System.getProperty("*"), ""));
        finalResult = new StringBuilder(finalResult.toString().toUpperCase(Locale.ROOT));
        return finalResult.toString();
    }


    public String codeRandomizer() {
        StringBuilder plug = new StringBuilder();
        Random random = new Random();
        StringBuilder finalCode = new StringBuilder();
        int num;
        plug.append(alphabet);
        for (int j = 0; j < alphabet.length(); j++) {
            num = random.nextInt(plug.length());
            finalCode.append(plug.charAt(num));
            plug.delete(num, num + 1);
        }
        //System.out.println(finalCode);
        return finalCode.toString();
    }

    public String uniformCrossOver(String string1, String string2) {
        char[] stringChar1 = string1.toCharArray();
        char[] stringChar2 = string2.toCharArray();
        char[] finalChar = new char[string1.length()];
        StringBuilder finalString = new StringBuilder();
        int coef = 10;
        Random random = new Random();
        int num;
        for (int i = 0; i < coef; i++) {                //adding random 10 chars to final code
            do {
                num = random.nextInt(stringChar1.length);
            }
            while (stringChar1[num] == '\u0000');
            finalChar[num] = stringChar1[num];
            stringChar1[num] = '\u0000';

        }

        for (int i = 0; i < stringChar2.length; i++)    //deleting extra chars from 2cnd string
        {
            for (int j = 0; j < stringChar2.length; j++) {
                if (stringChar2[i] == finalChar[j]) {
                    stringChar2[i] = '\u0000';
                }
            }
        }

        for (int i = 0; i < finalChar.length; i++)        // adding chars from 2cnd string and deleting these chars from 2cnd string
        {
            if (finalChar[i] == '\u0000' && stringChar2[i] != '\u0000') {
                finalChar[i] = stringChar2[i];
                stringChar2[i] = '\u0000';
            }
        }

        for (int i = 0; i < finalChar.length; i++) {
            if (finalChar[i] == '\u0000') {
                for (int j = 0; j < stringChar2.length; j++) {
                    if (stringChar2[j] != '\u0000') {
                        finalChar[i] = stringChar2[j];
                        stringChar2[j] = '\u0000';
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < finalChar.length; i++) {
            finalString.append(finalChar[i]);

        }
        return finalString.toString();
    }


}

class Sample {
    String code;
    Double grade;

    public Sample(String code, Double grade) {
        this.code = code;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public Double getGrade() {
        return grade;
    }
}


class GeneticAlgorithmMain {
    public static void main(String[] args) throws IOException {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
//            //geneticAlgorithm.codeRandomizer(); //randomly replace letters in alphabet
        String string = geneticAlgorithm.codeRandomizer();
        String string2 = geneticAlgorithm.codeRandomizer();
//         geneticAlgorithm.decoder("EKMFLGDQVZNTOWYHXUSPAIBRCJ
//                                   ORCKLHIAQJTPMYEWBGSUVZXNFD");
        //geneticAlgorithm.convertText(); // split text to 3-gram

        geneticAlgorithm.geneticAlgoriyhm();

//        while (true) {
//            System.out.println(geneticAlgorithm.fitnessCounter(geneticAlgorithm.percentNgram(geneticAlgorithm.ngramCounter(geneticAlgorithm.decoder(geneticAlgorithm.codeRandomizer())))));
//        }

//

    }

}