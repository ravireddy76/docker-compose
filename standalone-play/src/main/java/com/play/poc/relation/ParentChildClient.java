package com.play.poc.relation;

public class ParentChildClient {

    public static void main(String[] args){
        try{

            TParent tParent = new TParent();
            tParent.setName("Test");
            tParent.setAge("26");

            TChild tChild = (TChild) tParent;

            System.out.println("Name ::"+tChild.getName());
            System.out.println("Age :: "+tChild.getAge());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
