package org.quaere.quaere4objects;

import static org.quaere.DSL.*;
import org.junit.Test;
import org.quaere.model.Product;
import org.quaere.Variant;

public class JoinOperatorsTestCase {
    //  efficiently join elements of two sequences based on equality between key expressions over the two.
    @Test
    public void canEfficentlyJoinElementsOfTwoSequencesBasedOnEqualityBetweenKeyExpresionsOverTheTwo_linq102()
    {
        String[] categories={
                "Beverages",
                "Condiments",
                "Vegetables",
                "Dairy Products",
                "Seafood"
        };
        Product[] products= Product.getAllProducts();
        Iterable<Variant> q=
                from("c").in(categories).
                join("p").in(products).on(eq("c","p.getCategory()")).
                select(
                    create(
                        property("category","c"),
                        property("p.getProductName()")
                    )
                );
        for (Variant v: q) {
            System.out.println(String.format("%s: %s",v.get("category"),v.get("productName")));
        }
    }
    @Test
    public void canUseGroupJoinToGetAllTheProductsThatMatchAGivenCategoryBundledAsASequence_linq103()
    {
        String[] categories={
                "Beverages",
                "Condiments",
                "Vegetables",
                "Dairy Products",
                "Seafood"
        };
        Product[] products= Product.getAllProducts();
        Iterable<Variant> q=
                from("c").in(categories).
                join("p").in(products).on(eq("c","p.getCategory()")).into("ps").
                select(
                    create(
                        property("category","c"),
                        property("products","ps")
                    )
                );
        for (Variant v: q){
            System.out.println(String.format("%s: %s",v.get("category"),v.get("products")));
        }
    }
    // TODO: Write a test case for linq104
}
