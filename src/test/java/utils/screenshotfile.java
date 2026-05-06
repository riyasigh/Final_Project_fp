package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class screenshotfile {

    public static void takeScreenshot(WebDriver driver, String name) {

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            File dest = new File(
                    "screenshots/" + name + ".png"
            );

            dest.getParentFile().mkdirs(); // create folder if not exists
            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved: " + dest.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//package utils;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class screenshotfile {
//
//    // Folder where screenshots will be saved
////    private static final String SCREENSHOT_DIR = System.getProperty("user.dir")
////            + File.separator + "Screenshots" + File.separator;
//    private static final String SCREENSHOT_DIR = "src/test/resources/ScreenShots/";
//
//    // This makes sure old screenshots are deleted only once per run
//    private static boolean cleaned = false;
//
//    // Take screenshot and save it
//    public static void capture(WebDriver driver, String label) {
//
//        // If driver is not available skip screenshot
//        if (driver == null) {
//            System.out.println("Screenshot skipped: driver is null.");
//            return;
//        }
//
//        // Delete old screenshots only on first capture of the run
//        if (!cleaned) {
//            deleteOldScreenshots();
//            cleaned = true;
//        }
//
//        try {
//            // Step 1 - Cast driver to TakesScreenshot
//            TakesScreenshot ts = (TakesScreenshot) driver;
//
//            // Step 2 - Capture screenshot as file
//            File src = ts.getScreenshotAs(OutputType.FILE);
//
//            // Step 3 - Create filename with timestamp
//            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            String filename = label + "_" + timestamp + ".png";
//
//            // Step 4 - Define where to save
//            File target = new File(SCREENSHOT_DIR + filename);
//
//            // Step 5 - Create folder if not exists
//            target.getParentFile().mkdirs();
//
//            // Step 6 - Copy screenshot to target location
//            FileUtils.copyFile(src, target);
//
//            System.out.println("Screenshot saved: " + filename);
//
//        } catch (IOException e) {
//            System.out.println("Screenshot failed: " + e.getMessage());
//        }
//    }
//
//    // Delete all old screenshots from folder
//    private static void deleteOldScreenshots() {
//        File folder = new File(SCREENSHOT_DIR);
//        if (folder.exists()) {
//            File[] files = folder.listFiles();
//            if (files != null) {
//                for (File f : files) {
//                    f.delete();
//                    System.out.println("Deleted old screenshot: " + f.getName());
//                }
//            }
//        }
//    }
//}
