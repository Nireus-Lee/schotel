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
		return entityManager;
	}

	protected void closeEntityManager() {
		entityManager.close();
		entityManager = null;
	}

	public EntityManager createEntityManager() {
		return entityManager;
	}

	public void setEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
}
