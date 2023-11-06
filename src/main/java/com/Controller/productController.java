package com.Controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.Enitity.Category;
import com.Enitity.Product;
import com.Repository.CategoryRepository;
import com.Repository.ProductRepository;

@Controller
public class productController {
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CategoryRepository catRepo;
	
	@PostMapping("/saveProduct")
	public String saveProd(@RequestParam("pName")String pName,
			@RequestParam("pDesc")String pDesc,
			@RequestParam("pPhoto")MultipartFile pPhoto,
			@RequestParam("pPrice")String pPrice,
			@RequestParam("cid")String catId,
			@RequestParam("pDiscount")String pDiscount,
			@RequestParam("pQuantity")String pQuantity) throws IOException, SerialException, SQLException {
		Product p=new Product();
		p.setPName(pName); 
		p.setPDesc(pDesc);
		p.setPPrice(pPrice);
		p.setPDiscount(pDiscount);
		p.setPQuantity(pQuantity);
		
		
				
		int cid=Integer.parseInt(catId);
		Category cat=catRepo.findById(cid).get();
		
		p.setCategory(cat);
		
		byte[] bytes = pPhoto.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        p.setPPhoto(blob);
		
        productRepo.save(p);
        
		return "redirect:/admin";
	}
	
	@GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("pId") int id) throws IOException, SQLException
    {
        Product image = productRepo.findById(id).get();
        byte [] imageBytes = null;
        imageBytes = image.getPPhoto().getBytes(1,(int) image.getPPhoto().length());
        return ResponseEntity.ok().contentType(org.springframework.http.MediaType.IMAGE_JPEG).body(imageBytes);
    }
}
