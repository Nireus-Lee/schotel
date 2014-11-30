/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao {

	@PersistenceContext
	protected EntityManager entityManager;
	
	public EntityManager getEm() {
		//System.out.print("entity manager: " + entityManager);
		return entityManager;
	}

}
