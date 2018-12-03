import java.io.*;

/**
 * The class consists of basic functions needed to operate files
 *
 * @author group 66
 */
public class FileFunction {
    /**
     * read file in lines
     *
     * @param fileName file name
     * @return string
     */
    public static String readFile(String fileName) {
        String result = "";
        try {
            File file = new File(fileName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "gbk");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                result = result + s;
            }
            bufferedReader.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Overwrite the original file
     *
     * @param fileName    file name
     * @param currentData current data
     */
    public static void writeFile(String fileName, String currentData) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(fileName);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(currentData.getBytes("gbk"));
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add content to the end of the file
     *
     * @param filePath file path
     * @param content  content needed
     */
    public static void chaseWrite(String filePath, String content) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            content += "\r\n";
            fw.write(content);
            fw.close();
        } catch (IOException e) {

        }
    }
}
