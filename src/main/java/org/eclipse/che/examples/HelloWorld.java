package org.eclipse.che.examples;

//import com.amazon.client.metrics.internal.BasicMetricEvent;
//import com.amazon.client.metrics.transport.ProvidedOAuthMetricsTransport;
//import com.amazon.rabbit.android.data.sync.SntpClient;
//import com.facebook.common.util.UriUtil;
//import com.facebook.imagepipeline.request.MediaVariations;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okio.ByteString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

public class HelloWorld {
    private Regex trimRegex = new Regex("\\s+");

    public final Regex getTrimRegex() {
        return this.trimRegex;
    }
    
    public static void main(String... argvs) {
        String time;
        Map<String, List<String>> headers=new HashMap<String, List<String>>() {};  
        List vals = new ArrayList();
        vals.add("Atna|EwICIMVUkeCdoTBd7CH51HIPMWMIRBQvRqDQmvq54rdTqj4vz9nAyo-FB6-KRJzZiinoiBdag2DJjAIiWgWddJYM-50j35bDwpjukE-dW_XNe1NsVrgEnU3mkouQHcWooJs4eDnv4XXJ1dytM-ffClZ9keLmfL8drpQdwOgEcFM47_280tSyhh6L-iKrGTo5vLg-QKNGOrPrtpddl8cmLnzZ5OOoW8-ULcSkivfd_K_ohLZJLqKANyzyi4e-5IVibM3__TYYt9Pd-kazV8Dkhk7KbbHGv35Tq6qL_BZAbSQjK2V2jUstcJvZWKElI6Djrfk4GjvF0DRuXWryxr-y7SJsiGd_ugpzD_yqyOKI_FbH7Rs5yQ");
        
        headers.put("x-amz-access-token", vals);
        String host = "https://flex-capacity-na.amazon.com";
        String canonicalPath = "/GetOffersForProvider?21&serviceAreaIds=21&apiVersion=V2";
        DateTimeFormatter timestampFormater = DateTimeFormat.forPattern("yyyyMMdd'T'HHmmss'Z'");
        time = timestampFormater.print(DateTime.now());
        String r0[];
        r0 = new String[4];
        String method = time.substring(0, 8);
        Intrinsics.checkExpressionValueIsNotNull(method, "(this as java.lang.Strin…ing(startIndex, endIndex)");
//        Pair canonicalRequest = getCanonicalRequest$RabbitAndroidFramework_release(method, host2, canonicalPath, headers, time);
        Pair canonicalRequest = getCanonicalRequest$RabbitAndroidFramework_release(method, host, canonicalPath, headers, time);
        r0[0] = "a";
        r0[1] = method;
        r0[2] = "rabbit_request";
        r0[3] = getStringToSign$RabbitAndroidFramework_release((String) canonicalRequest.getFirst(), time);
//        
        byte[] signatureBytes = sign$RabbitAndroidFramework_release(r0);
        String signature = ByteString.of(Arrays.copyOf(signatureBytes, signatureBytes.length)).hex();

        System.out.println("ro[1] - " + r0[1]);
        System.out.println("r0[2] - " + r0[2]);
        System.out.println("r0[3] - " + r0[3]);
        System.out.println("signatureBytes - " + signatureBytes);
        System.out.println("signature - " + signature);
    }
    
    public final static String getStringToSign$RabbitAndroidFramework_release(String canonicalRequest, String time) {
        Intrinsics.checkParameterIsNotNull(canonicalRequest, "canonicalRequest");
        Intrinsics.checkParameterIsNotNull(time, "time");
        return "RABBIT3-HMAC-SHA256\n" + time + '\n' + hexSha256$RabbitAndroidFramework_release(canonicalRequest);
    }
    
    public final static Pair<String, String> getCanonicalRequest$RabbitAndroidFramework_release(String method, String host, String canonicalPath, Map<String, List<String>> headers, String time) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(canonicalPath, "canonicalPath");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(time, "time");
        headers.put("x-amz-date", CollectionsKt.listOf(time));
        headers.put("host", CollectionsKt.listOf(host));
        Map<String, List<String>> $receiver$iv = headers;
        LinkedHashMap result$iv = new LinkedHashMap();
        for (Entry entry$iv : $receiver$iv.entrySet()) {
            if (shouldSign$RabbitAndroidFramework_release((String) entry$iv.getKey())) {
                result$iv.put(entry$iv.getKey(), entry$iv.getValue());
            }
        }
        Map filteredHeaders = result$iv;
        char canonicalHeaderString = getCanonicalHeaderString$RabbitAndroidFramework_release(filteredHeaders);
        String signedHeaders = getSignedHeadersString$RabbitAndroidFramework_release(filteredHeaders);
        return new Pair(method + '\n' + canonicalPath + '\n' + canonicalHeaderString + '\n' + signedHeaders, signedHeaders);
    }
    
    public final static String getSignedHeadersString$RabbitAndroidFramework_release(Map<String, ? extends List<String>> headers) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        String jsonString = new JSONObject(headers).toString();   
        return jsonString;
    }
    
    public final static char getCanonicalHeaderString$RabbitAndroidFramework_release(Map<String, ? extends List<String>> headers) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Collection destination$iv$iv = new ArrayList(headers.size());
        for (Entry item$iv$iv : headers.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            String str = (String) item$iv$iv.getKey();
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str = str.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
//            destination$iv$iv.add(stringBuilder.append(str).append(':').append(CollectionsKt.joinToString((Iterable) item$iv$iv.getValue(), BasicMetricEvent.LIST_DELIMITER, null, null, 0, null, new C1460x541fa53a(this), 30)).append('\n').toString());
        }
//        return CollectionsKt.joinToString((List) destination$iv$iv, "", null, null, 0, null, null, 62);
        return 'a';
    }
    
    public final static boolean shouldSign$RabbitAndroidFramework_release(String header) {
        Intrinsics.checkParameterIsNotNull(header, "header");
//        if (header.equals("date") || header.equals("host") || StringsKt.startsWith$default$3705f858(header, "x-amz", false, 2)) {
//            return true;
//        }
        if (header.equals("date") || header.equals("host")) {
          return true;
        }
        
        return false;
    }
    
    public final static byte[] sign$RabbitAndroidFramework_release(String... keys) {
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        Iterator it = ArrayIteratorKt.iterator(keys);
        String str = (String) it.next();
        Charset charset = Charsets.UTF_8;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] key = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(key, "(this as java.lang.String).getBytes(charset)");
        while (it.hasNext()) {
            str = (String) it.next();
            charset = Charsets.UTF_8;
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            Object bytes = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            key = hmacSha256$RabbitAndroidFramework_release((byte[]) bytes, key);
        }
        return key;
    }
    
    public final static byte[] hmacSha256$RabbitAndroidFramework_release(byte[] data, byte[] key) {
//        Intrinsics.checkParameterIsNotNull(data, UriUtil.DATA_SCHEME);
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key, "HmacSHA256"));
            Object doFinal = mac.doFinal(data);
            Intrinsics.checkExpressionValueIsNotNull(doFinal, "mac.doFinal(data)");
            return (byte[]) doFinal;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
    
    public final static String hexSha256$RabbitAndroidFramework_release(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        String hex = ByteString.encodeUtf8(value).sha256().hex();
        Intrinsics.checkExpressionValueIsNotNull(hex, "ByteString.encodeUtf8(value).sha256().hex()");
        return hex;
    }
}
