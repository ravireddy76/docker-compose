package com.play.poc.encryption;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5EncryptionClient {
    public static void main(String[] args) {
        try{

            String inputValue = "fName+lName+03/03/2020+2728273+999999";
            String md5Value = DigestUtils.md5Hex(inputValue);
            System.out.println(">>> MD5 value :: "+md5Value);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
