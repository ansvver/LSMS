package library.seat.manage.config;

import library.seat.manage.dao.IDeskDao;
import library.seat.manage.dao.IOrderDao;
import library.seat.manage.dao.IUserDao;
import library.seat.manage.dao.impl.DeskDaoImpl;
import library.seat.manage.dao.impl.OrderDaoImpl;
import library.seat.manage.dao.impl.UserDaoImpl;
import library.seat.manage.service.IDeskService;
import library.seat.manage.service.IOrderService;
import library.seat.manage.service.IUserService;
import library.seat.manage.service.impl.DeskServiceImpl;
import library.seat.manage.service.impl.OrderServiceImpl;
import library.seat.manage.service.impl.UserServiceImpl;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class GuicyDummyModel extends AbstractModule {

    public void configure() {
    	// dao binding
    	bind(IUserDao.class).to(UserDaoImpl.class).in(Singleton.class);
    	bind(IDeskDao.class).to(DeskDaoImpl.class).in(Singleton.class);
    	bind(IOrderDao.class).to(OrderDaoImpl.class).in(Singleton.class);
    	
    	// service binding
    	bind(IUserService.class).to(UserServiceImpl.class).in(Singleton.class);
    	bind(IDeskService.class).to(DeskServiceImpl.class).in(Singleton.class);
    	bind(IOrderService.class).to(OrderServiceImpl.class).in(Singleton.class);
    }
}