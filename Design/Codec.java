package Amazon_Prepare.Design;

import java.util.HashMap;
import java.util.Map;

public class Codec {

    Map<String, String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int hashCode = longUrl.hashCode();
//        String shortUrl = toBase36(hashCode);
        String shortUrl = Integer.toHexString(hashCode);
        map.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }

    private String toBase36(int hashCode) {
        StringBuilder result = new StringBuilder();
        while (hashCode > 0) {
            int rem = hashCode % 36;
            if (rem < 10) result.append('0' + rem);
            else result.append('a' + rem - 10);
            hashCode /= 36;
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        codec.decode(codec.encode("https://leetcode.com/problemsW"));
    }
}
