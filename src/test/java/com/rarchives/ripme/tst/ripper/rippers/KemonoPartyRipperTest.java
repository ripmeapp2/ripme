package com.rarchives.ripme.tst.ripper.rippers;

import com.rarchives.ripme.ripper.rippers.KemonoPartyRipper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KemonoPartyRipperTest extends RippersTest {
    @Test
    public void testRip() throws IOException, URISyntaxException {
        URL url = new URI("https://kemono.su/patreon/user/7874509").toURL();
        KemonoPartyRipper ripper = new KemonoPartyRipper(url);
        testRipper(ripper);
    }

    @Test
    public void testUrlParsing() throws IOException, URISyntaxException {
        String expectedGid = "patreon_7874509";
        String[] urls = new String[]{
                "https://kemono.su/patreon/user/7874509", // normal url
                "http://kemono.su/patreon/user/7874509", // http, not https
                "https://kemono.su/patreon/user/7874509/", // with slash at the end
                "https://kemono.su/patreon/user/7874509?whatever=abc", // with url params
                "https://kemono.party/patreon/user/7874509", // alternate domain
        };
        for (String stringUrl : urls) {
            URL url = new URI(stringUrl).toURL();
            KemonoPartyRipper ripper = new KemonoPartyRipper(url);
            assertTrue(ripper.canRip(url));
            assertEquals(expectedGid, ripper.getGID(url));
        }
    }
}