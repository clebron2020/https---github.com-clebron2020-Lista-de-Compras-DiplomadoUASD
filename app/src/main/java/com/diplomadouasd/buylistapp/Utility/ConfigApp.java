package com.diplomadouasd.buylistapp.Utility;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.diplomadouasd.buylistapp.Model.Entities.Product;
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.ViewModel.ProductCategoryViewModel;
import com.diplomadouasd.buylistapp.ViewModel.ProductViewModel;
import java.util.ArrayList;
import java.util.List;

public class ConfigApp
{
    Context _context;
    ProductCategoryViewModel productCategoryViewModel;
    ProductViewModel productViewModel;
    List<Product> productList = new ArrayList<>();
    List<ProductCategory> productCategoryList = new ArrayList<>();

    public ConfigApp(ProductCategoryViewModel productCategoryViewModel, ProductViewModel productViewModel,Context context) {
        this.productCategoryViewModel = productCategoryViewModel;
        this.productViewModel = productViewModel;
        this._context = context;
    }

    public Boolean CreateProductCategories(){
        Boolean result  = true;
        this.productCategoryList.add(new ProductCategory(1,"Carnes",true));
        this.productCategoryList.add(new ProductCategory(2,"Embutidos",true));
        this.productCategoryList.add(new ProductCategory(3,"Frutas",true));
        this.productCategoryList.add(new ProductCategory(4,"Verduras",true));
        this.productCategoryList.add(new ProductCategory(5,"Panadería",true));
        this.productCategoryList.add(new ProductCategory(6,"Dulces",true));
        this.productCategoryList.add(new ProductCategory(7,"Huevos",true));
        this.productCategoryList.add(new ProductCategory(8,"Lácteos",true));
        this.productCategoryList.add(new ProductCategory(9,"Café",true));
        this.productCategoryList.add(new ProductCategory(10,"Aceite",true));
        this.productCategoryList.add(new ProductCategory(11,"Pasta",true));
        this.productCategoryList.add(new ProductCategory(12,"Legumbres",true));
        this.productCategoryList.add(new ProductCategory(13,"Conservas",true));
        this.productCategoryList.add(new ProductCategory(14,"Zumos",true));
        this.productCategoryList.add(new ProductCategory(15,"Bebidas",true));
        this.productCategoryList.add(new ProductCategory(16,"Infantil",true));
        this.productCategoryList.add(new ProductCategory(17,"Cosmética",true));
		this.productCategoryList.add(new ProductCategory(18,"Hogar",true));
		this.productCategoryList.add(new ProductCategory(19,"Limpieza",true));

        for (ProductCategory p:productCategoryList)
            productCategoryViewModel.AddNewProductCategory(p);

        return
                result;
    }

    public Boolean CreateProducts(){
        Boolean result  = true;
        //Carnes
        Bitmap BitMapMeet = BitmapFactory.decodeResource(_context.getResources(), R.drawable.meat_food);
        byte[] ImageMeet = BitMapUtility.getBytes(BitMapMeet);
        productList.add(new Product(1,"Res",ImageMeet,true));
        productList.add(new Product(1,"Cerdo",ImageMeet,true));
        productList.add(new Product(1,"Pollo",ImageMeet,true));
        productList.add(new Product(1,"Pavo",ImageMeet,true));
        //Fin Carnes

        //Embutidos
        Bitmap BitmapEmbutidos = BitmapFactory.decodeResource(_context.getResources(), R.drawable.food);
        byte[] ImageEmbutidos = BitMapUtility.getBytes(BitmapEmbutidos);
        productList.add(new Product(2,"Salami",ImageEmbutidos,true));
        productList.add(new Product(2,"Salchicha",ImageEmbutidos,true));
        productList.add(new Product(2,"Longaniza",ImageEmbutidos,true));
        productList.add(new Product(2,"Chuleta",ImageEmbutidos,true));
        productList.add(new Product(2,"Mortadela",ImageEmbutidos,true));
        productList.add(new Product(2,"Morcilla",ImageEmbutidos,true));
        productList.add(new Product(2,"Chorizo",ImageEmbutidos,true));
        //Fin Embutidos

        //Frutas
        Bitmap BitmapFrutas = BitmapFactory.decodeResource(_context.getResources(), R.drawable.fruit);
        byte[] ImageFturas = BitMapUtility.getBytes(BitmapFrutas);
        productList.add(new Product(3,"Aguacate ",ImageFturas,true));
        productList.add(new Product(3,"Albaricoque ",ImageFturas,true));
        productList.add(new Product(3,"Piña",ImageFturas,true));
        productList.add(new Product(3,"Arándano",ImageFturas,true));
        productList.add(new Product(3,"Banana",ImageFturas,true));
        productList.add(new Product(3,"Cereza ",ImageFturas,true));
        productList.add(new Product(3,"Ciruela",ImageFturas,true));
        productList.add(new Product(3,"Coco",ImageFturas,true));
        productList.add(new Product(3,"Melocotón ",ImageFturas,true));
        productList.add(new Product(3,"Frambuesa",ImageFturas,true));
        productList.add(new Product(3,"Fresa",ImageFturas,true));
        productList.add(new Product(3,"Granada",ImageFturas,true));
        productList.add(new Product(3,"Limón",ImageFturas,true));
        productList.add(new Product(3,"Mandarina",ImageFturas,true));
        productList.add(new Product(3,"Mango",ImageFturas,true));
        productList.add(new Product(3,"Manzana",ImageFturas,true));
        productList.add(new Product(3,"Maracuyá",ImageFturas,true));
        productList.add(new Product(3,"Melón",ImageFturas,true));
        productList.add(new Product(3,"Zarzamora",ImageFturas,true));
        productList.add(new Product(3,"Naranja",ImageFturas,true));
        productList.add(new Product(3,"Pera",ImageFturas,true));
        productList.add(new Product(3,"Toronja",ImageFturas,true));
        productList.add(new Product(3,"Sandía",ImageFturas,true));
        productList.add(new Product(3,"Lima",ImageFturas,true));
        productList.add(new Product(3,"Uva",ImageFturas,true));

        //Fin Frutas

        for (ProductCategory pCat:productCategoryList)
        {
            List<Product> ProductsToCreate = GetProductByCategoryId(pCat.getProductCatId());
            for (Product p:ProductsToCreate)
                productViewModel.AddNewProduct(p);
        }

        return  result;
    }

    private List<Product> GetProductByCategoryId(int CategoryId){
        List<Product> result = new ArrayList<>();
        for (Product product:this.productList)
        {
            if (product.getProductCatId()==CategoryId)
                result.add(product);
        }
        return
                result;
    }
}
