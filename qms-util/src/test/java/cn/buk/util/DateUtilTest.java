package cn.buk.util;

import org.junit.Ignore;
import org.junit.Test;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilTest {

	@Test
	public void testCreateDate() {
		
		Date date1 = DateUtil.createDate(2013, 12, 30);
		
		Format sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String strTestDate1 = sdf1.format(date1.getTime());

		assertTrue("yyyy-MM-dd is expected!",strTestDate1.compareTo("2013-12-30")==0);
		

		date1 = DateUtil.createDate(2013, 2, 30);
		
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		strTestDate1 = sdf1.format(date1.getTime());
		
		String msg = "2013-02-30 is expected, but " + strTestDate1 + " is found!";

		assertTrue(msg, strTestDate1.compareTo("2013-03-02")==0);
	}

	@Test
	@Ignore
	public void testGetSomedayAfterToday() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDays() {
		Date testedDate = DateUtil.createDate(2015, 12, 10);
		Date expectedDate = DateUtil.createDate(2015, 12, 15);		
		testedDate = DateUtil.addDays(testedDate, 5);
		assertTrue("addDays failed.", DateUtil.getDaySpan(testedDate, expectedDate) == 0);

		testedDate = DateUtil.addDays(testedDate, -5);
		testedDate = DateUtil.addDays(testedDate, 6);
		assertFalse("addDays failed.", DateUtil.getDaySpan(testedDate, expectedDate) == 0);
	}

	@Test
	public void testConvertEtermDate_CurYear_ReturnOk() {
		String etermDate = "12AUG";
        String dayOfWeek = "TU";
        String correctDate = DateUtil.convertEtermDate(etermDate, dayOfWeek, null);

        assertTrue(correctDate.equalsIgnoreCase("2014-08-12"));
	}
    @Test

    public void testConvertEtermDate_NextYear_ReturnOk() {
        String etermDate = "12AUG";
        String dayOfWeek = "WE";
        String correctDate = DateUtil.convertEtermDate(etermDate, dayOfWeek, null);

        assertTrue(correctDate.equalsIgnoreCase("2015-08-12"));
    }

    @Test
	public void testGetPastHours() {
		//2014-08-21T12:00:00 ->Thu Aug 21 00:00:00 CST 2014
        String originalString = "2014-08-21T15:00:00";
        try {
            Date date = DateUtil.convertToDate(originalString, "yyyy-MM-dd'T'HH:mm:ss");
            System.out.println(date);
            String newSting = DateUtil.formatDate(date, "yyyy-MM-dd'T'HH:mm:ss");
            String newSting2 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
            System.out.println(date);
            //System.out.println(originalString);
            System.out.println(newSting);
            System.out.println(newSting2);
            System.out.print(DateUtil.getCurDateTime());
//            System.out.println()
            assertTrue(originalString.equalsIgnoreCase(newSting));
        } catch (ParseException e) {

        }
    }

	@Test
	@Ignore
	public void testGetPastMinutes() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetDayOfWeek() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetDayOfWeekDesc() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDaySpan() {
		Date currentDate = DateUtil.createDate(2015, 10, 28);
		Date comparedDate = DateUtil.createDate(2015, 10, 10);		
		assertTrue("getDaySpan failed", DateUtil.getDaySpan(currentDate, comparedDate) == 18);
		
		Date comparedDate2 = DateUtil.createDate(2015, 11, 10);
		assertTrue("getDaySpan failed", DateUtil.getDaySpan(currentDate, comparedDate2) == -13);
		
	}

}
