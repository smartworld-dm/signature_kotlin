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
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import static kotlin.collections.CollectionsKt.listOf;
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
        String method = "GET";
        Intrinsics.checkExpressionValueIsNotNull(method, "(this as java.lang.Strin…ing(startIndex, endIndex)");
System.out.println("LOG: method - " + method);
        
        String time;
        DateTimeFormatter timestampFormater = DateTimeFormat.forPattern("yyyyMMdd'T'HHmmss'Z'");
//        time = timestampFormater.print(DateTime.now());
        time = "20181014T100617Z";
System.out.println("LOG: time - " + time);

        Map<String, String> headers = new LinkedHashMap(8);
        headers.put("Content-Type", "application/json");
        headers.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 7.1.2; SM-N955U Build/NMF26X) RabbitAndroid/3.3.115.0");
        headers.put("X-Flex-Client-Time", "1539291535546");
        headers.put("x-flex-instance-id", "33c09223-f6a4-488a-9136-60a46071689d");
        headers.put("host", "flex-capacity-na.amazon.co");
        headers.put("x-amz-access-token", "Atna|EwICIE9afyH834P1vcKVWTynSG9FdlqvW3FtBSQOgqJNcaHOrkeWfJJrEpmOmBzolnySKAWdGWoe_UWtxt8zHSGzM4JzawEyi9cbQ3reJiBPh5SoxEb85a3VSBLpHwmxRPGiiym7Jzi1J2ExfgXhV6-MErzuSUgwidf9STujeCwuhwdmDj6mkE2MSxN1mW5BmE4CysVu0BfN_yaZO5fPUy6-_--tHhTA-ZPwv-qfOFlqWiGufaaxSaLFnWSm0p3g8AKrjMOWxVq_3JEjbRpwVG8y8g_q3JVDHC5uDBgVYr9wp5DY3vfjG7yJxlHMhLJM4NfWL3ndLRjxmBu--BV0glh2eUMl");
        headers.put("x-amz-date", time);
        headers.put("x-amzn-requestid", "a1259f56-f2d6-4519-81c0-9795525d4f95");
        System.out.println("LOG: HEADER ENTRY - " + headers.entrySet());
        
        String host = "flex-capacity-na.amazon.com";
        String canonicalPath = "/GetOffersForProvider";
        
        Pair canonicalRequest = getCanonicalRequest(method, host, canonicalPath, headers, time);
        System.out.println("LOG: canonicalRequest.getFirst() - " + canonicalRequest.getFirst());
        
        String r0[];
        r0 = new String[3];
        r0[0] = time.substring(0, 8);
        r0[1] = "rabbit_request";
        r0[2] = getStringToSign((String) canonicalRequest.getFirst(), time);
        System.out.println("LOG: r0[2] - " + r0[2]);
        
        byte[] signatureBytes = sign(r0);
        System.out.println("LOG: signatureBytes - " + signatureBytes);
        
        String signature = ByteString.of(Arrays.copyOf(signatureBytes, signatureBytes.length)).hex();
        System.out.println("signature - " + signature);
    }
    
    public final static Pair<String, String> getCanonicalRequest(String method, String host, String canonicalPath, Map<String, String> headers, String time) {
        Map<String, String> receiver = headers;
        Map result = new LinkedHashMap();
        for (Entry entry : receiver.entrySet()) {
            if (shouldSign((String) entry.getKey()))
                result.put(entry.getKey(), entry.getValue());
        }
        Map filteredHeaders = result;
        
        String canonicalHeaderString = getCanonicalHeaderString(filteredHeaders);
        String signedHeaders = getSignedHeadersString(filteredHeaders);
System.out.println("LOG: canonical header - " + canonicalHeaderString);
System.out.println("LOG: signed header - " + signedHeaders);

        return new Pair(method + '\n' + canonicalPath + '\n' + canonicalHeaderString + '\n' + signedHeaders, signedHeaders);
    }
    
    public final static String getStringToSign(String canonicalRequest, String time) {
        Intrinsics.checkParameterIsNotNull(canonicalRequest, "canonicalRequest");
        Intrinsics.checkParameterIsNotNull(time, "time");
        return "RABBIT3-HMAC-SHA256\n" + time + '\n' + hexSha256(canonicalRequest);
    }
    
    public final static String getSignedHeadersString(Map<String, ? extends List<String>> headers) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Iterator iterator = headers.keySet().iterator();
        
        String str = "";
        
        for (String entry : headers.keySet())
            str = str.concat(entry).concat(";");
        
        str = str.substring(0, str.length() - 1);
        return str;
    }
    
    public final static String getCanonicalHeaderString(Map<String, ? extends List<String>> headers) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Collection destination = new ArrayList(headers.size());
        for (Entry item : headers.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            String str = (String) item.getKey();
            
            if (str == null) 
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            
            str = str.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
            
            destination.add(stringBuilder.append(str).append(':').append(item.getValue()).append('\n').toString());
        }
            
        String str = "";
        for (Object entry : destination)
            str = str.concat(entry.toString());
        
        return str;
    }
    
    public final static boolean shouldSign(String header) {
        Intrinsics.checkParameterIsNotNull(header, "header");

        if (header.equals("date") || header.equals("host") || header.startsWith("x-amz")) {
            return true;
        }
        
        return false;
    }
    
    public final static byte[] sign(String... keys) {
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
System.out.println("LOG: str - " + str);
            Object bytes = str.getBytes(charset);
System.out.println("LOG: bytes - " + bytes);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            key = hmacSha256((byte[]) bytes, key);
System.out.println("LOG: key - " + key);
        }
        return key;
    }
    
    public final static byte[] hmacSha256(byte[] data, byte[] key) {
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
    
    public final static String hexSha256(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        String hex = ByteString.encodeUtf8(value).sha256().hex();
        Intrinsics.checkExpressionValueIsNotNull(hex, "ByteString.encodeUtf8(value).sha256().hex()");
        return hex;
    }
}
