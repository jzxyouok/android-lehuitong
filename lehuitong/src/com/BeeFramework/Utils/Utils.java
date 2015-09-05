package com.BeeFramework.Utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import org.json.JSONException;
import org.json.JSONTokener;

/*
 *	 ______    ______    ______
 *	/\  __ \  /\  ___\  /\  ___\
 *	\ \  __<  \ \  __\_ \ \  __\_
 *	 \ \_____\ \ \_____\ \ \_____\
 *	  \/_____/  \/_____/  \/_____/
 *
 *
 *	Copyright (c) 2013-2014, {Bee} open source community
 *	http://www.bee-framework.com
 *
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a
 *	copy of this software and associated documentation files (the "Software"),
 *	to deal in the Software without restriction, including without limitation
 *	the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *	and/or sell copies of the Software, and to permit persons to whom the
 *	Software is furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 *	IN THE SOFTWARE.
 *
 *一个工具类，可以压缩图片，解析json数据
 */
public class Utils
{
	/**
	 * 将服务器的数据通过该方法解析出来
	 * @param responseBody  传入的数据
	 * @return   解析出来的数据
	 * @throws JSONException
	 */
    public static Object parseResponse(String responseBody) throws JSONException {
        Object result = null;
        //trim the string to prevent start with blank, and test if the string is valid JSON, because the parser don't do this :(. If Json is not valid this will return null
        responseBody = responseBody.trim();
        if(responseBody.startsWith("{") || responseBody.startsWith("[")) {
            result = new JSONTokener(responseBody).nextValue();
        }
        if (result == null) {
            result = responseBody;
        }
        return result;
    }
    /**
     * 压缩bitmap
     * 
     * @param bm  传入的bitmap，像素值
     * @param pixel
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bm,int pixel){
        int srcHeight = bm.getHeight();
        int srcWidth = bm.getWidth();


        if(srcHeight>pixel || srcWidth>pixel)
        {
            float scale_y = 0;
            float scale_x = 0;
            if (srcHeight > srcWidth)
            {
                scale_y = ((float)pixel)/srcHeight;
                scale_x = scale_y;
            }
            else
            {
                scale_x = ((float)pixel)/srcWidth;
                scale_y = scale_x;
            }

            Matrix  matrix = new Matrix();
            matrix.postScale(scale_x, scale_y);
            Bitmap dstbmp = Bitmap.createBitmap(bm, 0, 0, srcWidth, srcHeight, matrix, true);
            return dstbmp;
        }
        else{
            return Bitmap.createBitmap(bm);
        }
    }
    /**
     * 手动设置图片压缩比例
     * @param bm  传入的bitmap图片
     * @param dstHeight  设置的高度
     * @param dstWidth	 设置的宽度
     * @return 如果宽度大于高度或者高度大于宽度，都会按照图片本身大小进行压缩成手动设置的宽度和高度进行比例压缩
     */
    public static Bitmap scaleBitmap(Bitmap bm,int dstHeight,int dstWidth){
        if(bm == null) return null;//java.lang.NullPointerException
        int srcHeight = bm.getHeight();
        int srcWidth = bm.getWidth();
        if(srcHeight>dstHeight || srcWidth>dstWidth){
            float scale_y = ((float)dstHeight)/srcHeight;
            float scale_x = ((float)dstWidth)/srcWidth;
            float scale = scale_y<scale_x?scale_y:scale_x;
            Matrix  matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap dstbmp = Bitmap.createBitmap(bm, 0, 0, srcWidth, srcHeight, matrix, true);
            return dstbmp;
        }
        else{
            return Bitmap.createBitmap(bm);
        }
    }

}
