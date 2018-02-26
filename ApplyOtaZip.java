package your own packageName

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by lyue on 17-5-17.
 */

/**
 * 应用ota.zip
 */
public class ApplyOtaZip {
    public static void enforce(String updateZipPath){
       /* Runtime.getRuntime().exec("echo '--update_package="+ updateZipPath +"' > /cache/recovery/command");
        Runtime.getRuntime().exec("echo '--wipe_cache="+ updateZipPath +"' >> /cache/recovery/command");
        Runtime.getRuntime().exec("reboot recovery");*/
        //echo '--update_package=/cache/recovery/diwen_log' > /cache/recovery/command
        OutputStreamWriter writer = null;
        try {
            String otaPackage = "--update_package=" + updateZipPath;
            String forceReboot = "--force-reboot";
            File file = new File("/cache/recovery/command");
            writer = new FileWriter(file);
            writer.write(otaPackage, 0, otaPackage.length());
            writer.write("\n");
            writer.write(forceReboot, 0, forceReboot.length());
            writer.write("\n");
            writer.flush();
            writer.close();
            Runtime.getRuntime().exec("reboot recovery");
        }
        catch (Exception e){
            Log.e("ApplyOtaZip", "enforce() update exception!");
        }
        finally {
            if(null != writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
