package com.voidmain.progaurd.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.voidmain.progaurd.dao.DAO;
import com.voidmain.progaurd.entity.Gift;
import com.voidmain.progaurd.entity.Transaction;
import com.voidmain.progaurd.entity.User;
import com.voidmain.progaurd.entity.User_Event;
import com.voidmain.progaurd.util.Constants;

public class SpamUtil {

	public static int findSpamUserBasedOnLoginHistory(String userName)
	{
		int result=1;
		
		User user=DAO.getUserById(userName);
		
		Date currentDate=new Date();
	
	    long difference = currentDate.getTime()-user.getRegDate().getTime();
        
	    long totalDays= difference / (24 * 60 * 60 * 1000);
	    long loggedInDays=DAO.getUserLoginHistorys().size();
		long notloggedinDays=totalDays-loggedInDays;
		
		System.out.println(userName+" logged in days "+loggedInDays);
		System.out.println("total days of profile:\t"+totalDays);
		System.out.println("not logged in days :\t"+notloggedinDays);
		
		System.out.println(" minimum no of percentage days to login :\t"+Constants.loginDaysPer);
		
		System.out.println("r1:\t"+notloggedinDays);
		System.out.println("r2:\t"+totalDays);
		
		float res1=(float)notloggedinDays/(float)totalDays;
		System.out.println("res1:\t"+res1);
		
		float res2=res1*100;
		System.out.println("res2:\t"+res2);
		
		if(Constants.loginDaysPer>res2)
		{
			result=0;
		}
		
		return result;
	}
	
	public static int findSpamUserBasedOnFriends(String userName)
	{	
		int noOfFriends=DAO.getFriendsList(userName).size();
		
		System.out.println(noOfFriends);
		
		if(Constants.noOfFirends>noOfFriends)
		{
			return 0;
		}
		
		return 1;
	}
	
	public static int findSpamUserBasedOnServices(String userName)
	{	
		int noOfServices=DAO.getUser_ServiceList(userName).size();
		
		System.out.println(noOfServices);
		
		if(Constants.noOfServices>=DAO.getUser_ServiceList(userName).size())
		{
			return 0;
		}
		
		return 1;
	}
	
	public static int findSpamUserBasedOnTransaction(String userName)
	{	
		int result=1;
		
		Set<Transaction> transactions=DAO.getUserById(userName).getTransactions();
		
		System.out.println("transactions size is :\t"+transactions.size());
		
		int noOfReceivedTransactions=0;
		
		for(Transaction transaction : transactions)
		{
			if(transaction.getType().equals("received"))
			{
				noOfReceivedTransactions++;
			}
		}
		
		//====================================================================
		
		int noOfEvents=DAO.getUser_EventList(userName).size();
		
		System.out.println(noOfEvents+"\t"+noOfReceivedTransactions);
		
		if(noOfEvents>noOfReceivedTransactions)
		{
			result=0;
		}
		
		return result;
	}
	
	public static int findSpamUserBasedOnSpendAmmount(String userName)
	{	
		float transactionAmount=0;
		
		Set<Transaction> transactions=DAO.getUserById(userName).getTransactions();
		
		System.out.println("transactions size is :\t"+transactions.size());
		
		for(Transaction transaction : transactions)
		{
			if(transaction.getType().equals("received"))
			{
				transactionAmount=transactionAmount+transaction.getAmount();
			}
		}
		
		System.out.println("total transaction amount \t"+transactionAmount);
		
		//====================================================================
		
		float user_EventAmount=0;
		
		List<User_Event> user_Events=DAO.getUser_EventList(userName);
		
		for(User_Event user_Event : user_Events)
		{
			System.out.println("user event each form "+user_Event);
			
			int eventID=user_Event.geteId();
			
			System.out.println("event id is :\t"+eventID);
			
			user_EventAmount=user_EventAmount+DAO.getEventById(eventID).getPrice();
		}
		
		System.out.println("user events amount \t"+user_EventAmount);
		
		//=========================================================================
		
		float userGiftAmount=0;
		
		List<Gift> gifts=DAO.getUserGiftedList(userName);
		
		for(Gift gift : gifts)
		{
			userGiftAmount=userGiftAmount+gift.getAmount();
		}
		
		System.out.println("user gifting ammount "+userGiftAmount);
		
		if(transactionAmount<user_EventAmount || transactionAmount<userGiftAmount)
		{
			return 0;
		}
		
		return 1;
	}
	
	public static boolean isSpamUser(String userName)
	{
		boolean flag=false;
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		
		list.add(findSpamUserBasedOnLoginHistory(userName));
		list.add(findSpamUserBasedOnFriends(userName));
		list.add(findSpamUserBasedOnServices(userName));
		list.add(findSpamUserBasedOnTransaction(userName));
		list.add(findSpamUserBasedOnSpendAmmount(userName));
		
		System.out.println("spam results is :\t"+list);
		
		int count=0;
		
		for(int i : list)
		{
			if(i==0)
			{
				count++;
			}
		}
		
		if(count>=3)
		{
			flag=true;
		}
		
		return flag;
	}
}
