package data.teamdata;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class Convert {
	
	public static void main(String[] args) throws Exception
	{
		File file = new File("C:/NBAData/teams");
		File[] files = file.listFiles();
		for (File f : files)
		{
			String filename = f.getName();
			if (filename.endsWith("svg"))
			{
				System.out.println(filename);
				String name = filename.substring(0,filename.length() - 4)+".png";
				BufferedReader br = new BufferedReader(new FileReader(f));
				ArrayList<String> strs = new ArrayList<String>();
				String s = null;
				while ((s = br.readLine()) != null)
				{
					strs.add(s);
				}
				StringBuilder sb = new StringBuilder();
                for (int i = 0 ;i < strs.size(); i++)
                {
                  sb.append(strs.get(i));
                  if ( i != strs.size() -1)
                  {
                	  sb.append("\n");
                  }
                }
                convertToPng(sb.toString(),"C:/Users/FD/Desktop/1/"+name);
			}
		}
	}
	 public static void convertToPng(String svgCode, String pngFilePath) throws IOException,
     TranscoderException {

 File file = new File(pngFilePath);

 FileOutputStream outputStream = null;
 try {
     file.createNewFile();
     outputStream = new FileOutputStream(file);
     convertToPng(svgCode, outputStream);
 } finally {
     if (outputStream != null) {
         try {
             outputStream.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
}

/**
* 将svgCode转换成png文件，直接输出到流中
* 
* @param svgCode svg代码
* @param outputStream 输出流
* @throws TranscoderException 异常
* @throws IOException io异常
*/
public static void convertToPng(String svgCode, OutputStream outputStream)
     throws TranscoderException, IOException {
 try {
     byte[] bytes = svgCode.getBytes("utf-8");
     PNGTranscoder t = new PNGTranscoder();
     TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
     TranscoderOutput output = new TranscoderOutput(outputStream);
     t.transcode(input, output);
     outputStream.flush();
 } finally {
     if (outputStream != null) {
         try {
             outputStream.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
}

}
