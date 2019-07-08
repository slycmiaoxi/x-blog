package cn.slycmiaoxi.elasticsearch;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

//链接工厂，池子的一些方法由我们实现
public class EsFactory extends BasePooledObjectFactory<Es> {

	// 创建对象
	@Override
	public Es create() throws Exception {
		return new Es();
	}

	// 包装对象
	@Override
	public PooledObject<Es> wrap(Es arg0) {
		return new DefaultPooledObject<Es>(arg0);
	}

	// 销毁对象关闭链接
	@Override
	public void destroyObject(PooledObject<Es> p) throws Exception {
		p.getObject().close();
		super.destroyObject(p);
	}

	// 校验对象是否正常
	@Override
	public boolean validateObject(PooledObject<Es> p) {
		return p.getObject().validate();
	}
}
