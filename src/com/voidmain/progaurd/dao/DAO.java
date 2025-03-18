package com.voidmain.progaurd.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.voidmain.progaurd.entity.Event;
import com.voidmain.progaurd.entity.Friends;
import com.voidmain.progaurd.entity.Gift;
import com.voidmain.progaurd.entity.Service;
import com.voidmain.progaurd.entity.Transaction;
import com.voidmain.progaurd.entity.User;
import com.voidmain.progaurd.entity.UserLoginHistory;
import com.voidmain.progaurd.entity.User_Event;
import com.voidmain.progaurd.entity.User_Service;

public class DAO {

	public static int addObject(Object object)
	{
		return HibernateTemplate.addObject(object);
	}

	public static int updateObject(Object object)
	{
		return HibernateTemplate.updateObject(object);
	}
	
	public static boolean isValidUser(String userName,String password)
	{
		boolean flag=false;

		User user=getUserById(userName);

		if(user!=null && user.getPassword().equals(password) && user.getStatus().equals("activated"))
		{
			flag=true;
		}

		return flag;
	}

	//===================================================================


	public static User getUserById(String userName)
	{
		return (User)HibernateTemplate.getObject(User.class,userName);
	}

	public static List<User> getUsersList()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From User");
	}	

	//=======================================================================

	public static Transaction getTransactionById(int tid)
	{
		return (Transaction)HibernateTemplate.getObject(Transaction.class,tid);
	}

	public static int deleteTransaction(int tid)
	{
		return HibernateTemplate.deleteObject(Transaction.class,tid);
	}

	public static List<Transaction> getTransactions()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From Transaction");
	}
	
	//========================================================================
	
	public static Service getServiceById(int sid)
	{
		return (Service)HibernateTemplate.getObject(Service.class,sid);
	}

	public static int deleteService(int sid)
	{
		return HibernateTemplate.deleteObject(Service.class,sid);
	}

	public static List<Service> getServices()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From Service");
	}
	
	//===================================================================
	
	public static Event getEventById(int eid)
	{
		return (Event)HibernateTemplate.getObject(Event.class,eid);
	}

	public static int deleteEvent(int eid)
	{
		return HibernateTemplate.deleteObject(Event.class,eid);
	}

	public static List<Event> getEvents()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From Event");
	}
	
	//===========================================================================
	
	public static Gift getGiftById(int gid)
	{
		return (Gift)HibernateTemplate.getObject(Gift.class,gid);
	}

	public static int deleteGift(int gid)
	{
		return HibernateTemplate.deleteObject(Gift.class,gid);
	}

	public static List<Gift> getGifts()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From Gift");
	}

	public static List<Gift> getUserReceivedGiftList(String userName)
	{
		List<Gift> gifts=new ArrayList<Gift>();

		List<Gift> giftList=(List)HibernateTemplate.getObjectListByQuery("From Gift");

		for(Gift gift : giftList)
		{
			if(gift.getToUser().equals(userName))
			{
				gifts.add(gift);
			}
		}

		return gifts;
	}
	
	public static List<Gift> getUserGiftedList(String userName)
	{
		List<Gift> gifts=new ArrayList<Gift>();

		List<Gift> giftList=(List)HibernateTemplate.getObjectListByQuery("From Gift");

		for(Gift gift : giftList)
		{
			if(gift.getFromUser().equals(userName))
			{
				gifts.add(gift);
			}
		}

		return gifts;
	}
	
	//================================================================================
	
	public static User_Event getUser_EventById(int ueId)
	{
		return (User_Event)HibernateTemplate.getObject(User_Event.class,ueId);
	}

	public static int deleteUser_Event(int ueId)
	{
		return HibernateTemplate.deleteObject(User_Event.class,ueId);
	}

	public static List<User_Event> getUser_Events()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From User_Event");
	}

	public static List<User_Event> getUser_EventList(String userName)
	{
		List<User_Event> user_Events=new ArrayList<User_Event>();

		List<User_Event> user_EventList=(List)HibernateTemplate.getObjectListByQuery("From User_Event");

		for(User_Event user_Event : user_EventList)
		{
			if(user_Event.getuId().equals(userName))
			{
				user_Events.add(user_Event);
			}
		}

		return user_Events;
	}
	
	public static List<User_Event> getEvent_UserList(int eid)
	{
		List<User_Event> user_Events=new ArrayList<User_Event>();

		List<User_Event> user_EventList=(List)HibernateTemplate.getObjectListByQuery("From User_Event");

		for(User_Event user_Event : user_EventList)
		{
			if(user_Event.geteId()==eid)
			{
				user_Events.add(user_Event);
			}
		}

		return user_Events;
	}
	
	//====================================================================
	
	public static User_Service getUser_ServiceById(int usId)
	{
		return (User_Service)HibernateTemplate.getObject(User_Service.class,usId);
	}

	public static int deleteUser_Service(int usId)
	{
		return HibernateTemplate.deleteObject(User_Service.class,usId);
	}

	public static List<User_Service> getUser_Services()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From User_Service");
	}

	public static List<User_Service> getUser_ServiceList(String userName)
	{
		List<User_Service> user_Services=new ArrayList<User_Service>();

		List<User_Service> user_ServiceList=(List)HibernateTemplate.getObjectListByQuery("From User_Service");

		for(User_Service user_Service : user_ServiceList)
		{
			if(user_Service.getuId().equals(userName))
			{
				user_Services.add(user_Service);
			}
		}

		return user_Services;
	}
	
	public static List<User_Service> getService_UserList(int sid)
	{
		List<User_Service> user_Services=new ArrayList<User_Service>();

		List<User_Service> user_ServiceList=(List)HibernateTemplate.getObjectListByQuery("From User_Service");

		for(User_Service user_Service : user_ServiceList)
		{
			if(user_Service.getsId()==sid)
			{
				user_Services.add(user_Service);
			}
		}
		
		return user_Services;
	}
	
	//=================================================================================
	
	public static UserLoginHistory getUserLoginHistoryById(int lhId)
	{
		return (UserLoginHistory)HibernateTemplate.getObject(UserLoginHistory.class,lhId);
	}

	public static int deleteUserLoginHistory(int lhId)
	{
		return HibernateTemplate.deleteObject(UserLoginHistory.class,lhId);
	}

	public static List<UserLoginHistory> getUserLoginHistorys()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From UserLoginHistory");
	}

	public static List<UserLoginHistory> getUserLoginHistoryList(String userName)
	{
		List<UserLoginHistory> userLoginHistorys=new ArrayList<UserLoginHistory>();

		List<UserLoginHistory> userLoginHistoryList=(List)HibernateTemplate.getObjectListByQuery("From UserLoginHistory");

		for(UserLoginHistory userLoginHistory : userLoginHistoryList)
		{
			if(userLoginHistory.getUserName().equals(userName))
			{
				userLoginHistorys.add(userLoginHistory);
			}
		}

		return userLoginHistorys;
	}
	
	//============================================================================
	
	public static Friends getFriendsById(int fid)
	{
		return (Friends)HibernateTemplate.getObject(Friends.class,fid);
	}

	public static int deleteFriends(int fid)
	{
		return HibernateTemplate.deleteObject(Friends.class,fid);
	}

	public static List<Friends> getFriendss()
	{
		return (List)HibernateTemplate.getObjectListByQuery("From Friends");
	}

	public static List<Friends> getFriendsList(String userName)
	{
		List<Friends> friendss=new ArrayList<Friends>();

		List<Friends> friendsList=(List)HibernateTemplate.getObjectListByQuery("From Friends");

		for(Friends friends : friendsList)
		{
			if(friends.getRequestedFrom().equals(userName) || friends.getRequestTo().equals(userName) && friends.getStatus().equals("accepted"))
			{
				friendss.add(friends);
			}
		}

		return friendss;
	}
	
	public static int activateUser(String username,String status)
	{
		int result=0;

		try {

			result=GetConnection.getConnection().createStatement().executeUpdate("update user set status='"+status+"' where username='"+username+"'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
