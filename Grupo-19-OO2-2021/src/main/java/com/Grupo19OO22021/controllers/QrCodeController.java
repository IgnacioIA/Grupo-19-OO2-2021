package com.Grupo19OO22021.controllers;



import java.awt.image.BufferedImage;

import javax.validation.Valid;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Grupo19OO22021.entities.Persona;
import com.Grupo19OO22021.services.QRCodeUtil;

import usuarios.util.StringUrl;

@Controller
@RequestMapping("/barcodes")
@RestController
public class QrCodeController {
	
	@PostMapping(value = "/zxing/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> zxingQRCode (@RequestBody String barcode,@Valid @ModelAttribute("stringUrl") StringUrl stringUrl) throws Exception{
		return SuccessResponse(QRCodeUtil.generateQRCode(stringUrl.getUrlDireccion()));
	}

	private ResponseEntity<BufferedImage> SuccessResponse(BufferedImage image) {
		return new ResponseEntity<>(image,HttpStatus.OK);
	}
	
	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter(){
		return new BufferedImageHttpMessageConverter();
	}
}
