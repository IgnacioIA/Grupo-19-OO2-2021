package com.Grupo19OO22021.services;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeUtil {

   public static BufferedImage generateQRCode(String urlText) throws Exception{
	   QRCodeWriter qrCodeWitrer = new QRCodeWriter();
	   BitMatrix bitMatrix = qrCodeWitrer.encode(urlText, BarcodeFormat.QR_CODE,200, 200);
   
	   return MatrixToImageWriter.toBufferedImage(bitMatrix);
   }


  
}
