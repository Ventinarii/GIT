package L5_6;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class L6{
    private final static String adress = "http://localhost:8098/buckets/s17149/keys/";
    public static void main(String[] args) throws Exception {
        List<String> valueA = Arrays.asList("A:puk_puk", "B:kto_tam?", "A:Drentwy_żart");
        List<String> valueB = Arrays.asList("Lorem_i_psu_czy_jakoś_tak", "NiE!_naprawdę?");
        //==============================================================================================================wrzuci do bazy dokument OK
        URL url = new URL(adress + "A");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        char[] content = valueA
                .stream()
                .reduce((a, b) -> a + ";" + b)
                .get()
                .toCharArray();
        osw.write(content);
        osw.flush();
        osw.close();
        System.out.println("wrzuci do bazy dokument");
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
        //==============================================================================================================pobierze go i wypisze OK
        url = new URL(adress + "A");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        System.out.println("pobierze go i wypisze");
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
        byte[] res = connection.getInputStream().readAllBytes();
        StringBuilder sb = new StringBuilder();
        for (byte e:res) {
            sb.append((char)e);
        }
        System.out.println(sb.toString());
        //==============================================================================================================zmodyfikuje go OK
        url = new URL(adress + "A");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        osw = new OutputStreamWriter(connection.getOutputStream());
        content = valueB
                .stream()
                .reduce((a, b) -> a + ";" + b)
                .get()
                .toCharArray();
        osw.write(content);
        osw.flush();
        osw.close();
        System.out.println("zmodyfikuje go");
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
        //==============================================================================================================następnie pobierze i wypisze OK
        url = new URL(adress + "A");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        System.out.println("wrzuci do bazy dokument");
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
        res = connection.getInputStream().readAllBytes();
        sb = new StringBuilder();
        for (byte e:res) {
            sb.append((char)e);
        }
        System.out.println(sb.toString());
        //==============================================================================================================a na końcu usunie go OK
        url = new URL(adress + "A");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setDoOutput(true);
        System.out.println("a na końcu usunie go");
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
        //==============================================================================================================i spróbuje pobrać z bazy OK
        url = new URL(adress + "A");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        System.out.println("i spróbuje pobrać z bazy");
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
        try{
            res = connection.getInputStream().readAllBytes();
            sb = new StringBuilder();
            for (byte e:res) {
                sb.append((char)e);
            }
            System.out.println(sb.toString());
        }catch (Exception ex){System.err.println(ex.toString());}
    }
}
