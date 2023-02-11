package com.example.oracledbdemo.dao.mapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RegExUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import com.example.oracledbdemo.dao.model.BooleanDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BooleanTest {

    @Test
    void test() {
        BooleanDto dto = BeanUtils.instantiate(BooleanDto.class);
        log.info(dto.toString());
        assert (true);
    }

    @Test
    void testDateTimeFormatter() {
        log.info(DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        assert (true);
    }

    @Test
    void testNullInteger() {
        Integer nullInteger = null;

        assert (nullInteger == 1);
    }

    @Test
    void testPattern() {
        assertFalse(Pattern.matches(".*\\.\\./.*", "/test"));
        assertTrue(Pattern.matches(".*\\.\\./.*", "../test/t"));
        assertFalse(Pattern.matches(".*\\.\\./.*", "..\\"));
        assertTrue(Pattern.matches(".*\\.\\.[/\\\\].*", "..\\"));
    }
    public static String getOriginFileName(String srcfile, String orgin) {
		return RegExUtils.replaceFirst(srcfile, "_c.mp4$",
				RegExUtils.replaceFirst(orgin, "[^\\.]*(\\.\\w+$)", "$1"));
	}
    @Test
    void testRegex() {
        // log.info(RegExUtils.replaceFirst("srcfile.getSrcfilename()", "[^\\.]*(\\.\\w+$)", "$1"));
        // log.info(RegExUtils.replaceFirst("abc345.mp04", "[^\\.]*(\\.\\w+$)", "$1"));
        log.info(this.getOriginFileName("test.mp4", "abc-985sd887-32.webm"));
        log.info(this.getOriginFileName("555-9861test_c.mp4", "abc-985sd887-32.webm"));
        assertTrue(true);
    }

    // public static void dumpHeap(String filePath, boolean live) throws IOException {
    //     MBeanServer server = ManagementFactory.getPlatformMBeanServer();
    //     HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
    //             server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
    //     mxBean.dumpHeap(filePath, live);
    // }

    // @Test
    // void testJavaDump() {
    //     this.dumpHeap("", true);

    //     assertTrue(true);
    // }
}
