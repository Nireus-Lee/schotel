/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.hotel.dao;

import cn.buk.hotel.util.EntityUtil;

import javax.persistence.EntityManager;

public abstract class AbstractDao {

	protected EntityManager entityManager;
	
	public EntityManager getEM() {
		if (entityManager == null) {
			entityManager = EntityUtil.getEntityManagerFactory().createEntityManager();
		}
		
		return entityManager;
	}

	public void setEM(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
}
