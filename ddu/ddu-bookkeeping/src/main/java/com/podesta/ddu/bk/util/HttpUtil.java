package com.podesta.ddu.bk.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * http操作类
 *
 */
public class HttpUtil {
	
	/**
	 * 根据url取得数据，支持gzip类网站
	 * @param url
	 * @param charset
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String getContentByUrl(String url,String charset) throws ParseException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		
		
		if(StringUtil.isEmpty(charset)){
			String defaultCharset="iso-8859-1";
			Header contentTypeHeader=response.getFirstHeader("Content-Type");
			String contentType=contentTypeHeader.getValue().toLowerCase();
			if(contentType.indexOf("gbk")>-1 || contentType.indexOf("gb2312") >-1 || contentType.indexOf("gb18030")>-1){
				defaultCharset="gb18030";
			}
			else if(contentType.indexOf("utf-8")>-1){
				defaultCharset="utf-8";
			}
			else if(contentType.indexOf("big5")>-1){
				defaultCharset="big5";
			}
			charset=defaultCharset;
		}
		Header contentEncoding=response.getFirstHeader("Content-Encoding");
		StatusLine line=response.getStatusLine();
		if(line.getStatusCode()==200)
		{
			HttpEntity entity = response.getEntity();
			InputStream is;
			if(contentEncoding!=null && contentEncoding.getValue().toLowerCase().equals("gzip"))
			{
				 is=new GZIPInputStream( entity.getContent());
			}
			else
			{
				is=entity.getContent();
			}
			String str=FileUtil.inputStream2String(is, charset);
			is.close();
			return str;
			 
		}
		return "";
	}
	
	
	public static String getContentByUrl(String url) throws ParseException, IOException
	{
		return getContentByUrl(url,"");
	}
	
	/**
	 * 保存远程文件到本地
	 * @param remoteFile 远程文件
	 * @param localFile  本地文件
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void saveRemoteFile(String remoteFile,String localFile) throws ParseException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(remoteFile);
		HttpResponse response = httpclient.execute(httpget);
		Header contentEncoding=response.getFirstHeader("Content-Encoding");
		StatusLine line=response.getStatusLine();
		if(line.getStatusCode()==200)
		{
			HttpEntity entity = response.getEntity();
			InputStream is;
			if(contentEncoding!=null && contentEncoding.getValue().toLowerCase().equals("gzip")){
				 is=new GZIPInputStream( entity.getContent());
			}
			else{
				is=entity.getContent();
			}
			FileUtil.createFolder(localFile, true);
			FileOutputStream fs = new FileOutputStream(localFile); 
			
//			int bytesum = 0; 此字段未用上 20170421
			int byteread = 0; 
			byte[] buffer = new byte[30000]; 
			while ((byteread = is.read(buffer)) != -1){ 
//				bytesum += byteread; // 字节数 文件大小 
				fs.write(buffer, 0, byteread); 
			} 
			is.close();
			fs.close();
		}
		
	}
	



}
