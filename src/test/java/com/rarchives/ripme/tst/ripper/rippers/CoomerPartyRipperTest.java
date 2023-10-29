package com.rarchives.ripme.tst.ripper.rippers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rarchives.ripme.ripper.rippers.CoomerPartyRipper;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

public class CoomerPartyRipperTest extends RippersTest {
    @Test
    public void testRip() throws IOException {
        URL url = new URL("https://coomer.su/onlyfans/user/soogsx");
        CoomerPartyRipper ripper = new CoomerPartyRipper(url);
        testRipper(ripper);
    }

    @Test
    public void testUrlParsing() throws IOException {
        String expectedGid = "onlyfans_soogsx";
        String[] urls = new String[]{
                "https://coomer.su/onlyfans/user/soogsx", // normal url
                "http://coomer.su/onlyfans/user/soogsx", // http, not https
                "https://coomer.su/onlyfans/user/soogsx/", // with slash at the end
                "https://coomer.su/onlyfans/user/soogsx?whatever=abc", // with url params
                "https://coomer.party/onlyfans/user/soogsx", // alternate domain
        };
        for (String stringUrl : urls) {
            URL url = new URL(stringUrl);
            CoomerPartyRipper ripper = new CoomerPartyRipper(url);
            assertTrue(ripper.canRip(url));
            assertEquals(expectedGid, ripper.getGID(url));
        }
    }
}