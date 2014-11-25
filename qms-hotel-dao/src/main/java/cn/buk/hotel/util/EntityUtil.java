/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.util;



import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityUtil {
	
	private static EntityManagerFactory emf;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("hotel");
		} catch (Exception e) {
			e.printStackTrace();
            System.err.println("Initial EntityManagerFactory creation failed." + e);
        }
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
	


}
