package Backend;


public class Article_Encryption_Test {


    public void test() throws Exception {


        Article myArticle = new Article();
        myArticle.setBody("This is my Decrypted Body");

        System.out.println("Setting article body to - "+myArticle.getBody());

        new Encryption().encryptBody(myArticle);
        System.out.println("Encrypting body - ");
        System.out.println(myArticle.getBody());
        System.out.println("Decrypting body - ");
        System.out.println(new Encryption().decryptArticle(myArticle));


        System.out.println("Article iv = "+myArticle.getIv());


    }
}
